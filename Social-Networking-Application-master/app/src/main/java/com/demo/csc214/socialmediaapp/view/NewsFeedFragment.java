package com.demo.csc214.socialmediaapp.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.DatabaseQueriesHandler;
import com.demo.csc214.socialmediaapp.controller.FollowTableModify;
import com.demo.csc214.socialmediaapp.controller.PostListOrganizer;
import com.demo.csc214.socialmediaapp.controller.PostQuery;
import com.demo.csc214.socialmediaapp.model.Database.FollowerDatabase;
import com.demo.csc214.socialmediaapp.model.Database.PostDatabase;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Post.Post;
import com.demo.csc214.socialmediaapp.model.Post.PostList;
import com.demo.csc214.socialmediaapp.model.Profile.ProfileList;

import java.util.List;

/**
 * Created by Sailesh on 4/20/18.
 */

public class NewsFeedFragment  extends ListFragment{

    View view;

    PostList list;

    int user_id;

    public final String USERID_KEY = "USER_ID_KEY";

    Button mButtonTop;
    ListView listView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new PostList();

        if (getArguments() != null) {
            user_id = getArguments().getInt(USERID_KEY, PostQuery.currentID);
            Log.i("Current User ID: ", Integer.toString(user_id));
        }

        List<Integer> listFollowed = FollowTableModify.getListOfFollowers(user_id, FollowerDatabase.getInstance(getContext()));

        for (int i : listFollowed) {
            Log.i("Currently Follow", Integer.toString(i));
        }

        //listFollowed.add(user_id);

        List<Post> listPosts = PostListOrganizer.getListOfPosts(listFollowed, PostDatabase.getInstance(getContext()));

        Log.i("Post List Length", Integer.toString(listPosts.size()));

        for (Post p : listPosts) {
            Log.i("Total Posts", p.toString());
        }

        List<Post> listSortPosts = PostListOrganizer.sortList(listPosts);

        list.postList = listSortPosts;


        for (Post p : list.postList) {
            Log.i("Post", p.toString());
        }

        PostAdapter adapter = new PostAdapter(getActivity(), R.layout.post_list_view, list.postList, user_id);

        setListAdapter(adapter);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.news_feed_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getActivity().findViewById(android.R.id.list);

        mButtonTop = view.findViewById(R.id.top_button_id);

        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.smoothScrollToPosition(0);
                Toast.makeText(getContext(), "Scroll to Top", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
