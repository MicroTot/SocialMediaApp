package com.demo.csc214.socialmediaapp.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.DatabaseQueriesHandler;
import com.demo.csc214.socialmediaapp.controller.PostQuery;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Post.Post;
import com.demo.csc214.socialmediaapp.model.Profile.Profile;
import com.demo.csc214.socialmediaapp.model.Profile.ProfileList;

/**
 * Created by Sailesh on 4/20/18.
 */

public class ListUsersFragment extends ListFragment{

    View view;

    ProfileList list;

    int user_id = 0;

    public final String USERID_KEY = "USER_ID_KEY";


    private static final String ARG_FIRSTNAME = "firstName";
    private static final String ARG_LASTNAME = "lastName";
    private static final String ARG_PROFILE = "profile_pic";
    private static final String ARG_HOMETOWN = "hometown";

    private static final String ARG_BIRTHDAY = "birthday";
    private static final String ARG_BIO = "bio";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ProfileList();

        if (getArguments() != null) {
            user_id = getArguments().getInt(USERID_KEY, PostQuery.currentID);
            Log.i("Current User ID: ", Integer.toString(user_id));
        }

        list.profileList = DatabaseQueriesHandler.getListOfProfile(ProfileDatabase.getInstance(getContext()));

        for (Profile profile : list.profileList) {
            if (profile.getUser_id() == user_id) {
                list.profileList.remove(profile);
                Log.i("Analysis", "Removed!");
            }
        }

        ProfileAdapter adapter = new ProfileAdapter(getActivity(), R.layout.profile_list_adapter_view, list.profileList, user_id);

        setListAdapter(adapter);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.list_user_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = getActivity().findViewById(android.R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("OnClickRegistered", "REGISTER!");
                Profile profile = (Profile) parent.getItemAtPosition(position);

                Log.i("Position: ", Integer.toString(position));

                ProfileDialogFragment frag = new ProfileDialogFragment();

                Bundle args = new Bundle();
                args.putInt(USERID_KEY, user_id);
                args.putString(ARG_FIRSTNAME, profile.getFirstName());
                args.putString(ARG_LASTNAME, profile.getLastName());
                args.putString(ARG_PROFILE, profile.getProfilePhoto());
                args.putString(ARG_HOMETOWN, profile.getHometown());
                args.putString(ARG_BIRTHDAY, profile.getBirthDate());
                args.putString(ARG_BIO, profile.getBio());

                frag.setArguments(args);
                frag.show(getActivity().getFragmentManager(), "tag");

            }
        });

    }


}
