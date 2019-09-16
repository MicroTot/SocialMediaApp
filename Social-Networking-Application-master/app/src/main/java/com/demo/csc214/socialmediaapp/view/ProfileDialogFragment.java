package com.demo.csc214.socialmediaapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.FollowTableModify;
import com.demo.csc214.socialmediaapp.model.Database.FollowerDatabase;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.FollowerEntity;

import java.io.File;

/**
 * Created by Sailesh on 4/20/18.
 */

public class ProfileDialogFragment extends DialogFragment {

    private static final String ARG_FIRSTNAME = "firstName";
    private static final String ARG_LASTNAME = "lastName";
    private static final String ARG_PROFILE = "profile_pic";
    private static final String ARG_HOMETOWN = "hometown";

    private static final String ARG_BIRTHDAY = "birthday";
    private static final String ARG_BIO = "bio";

    public final String USERID_KEY = "USER_ID_KEY";

    int user_id;

    boolean currentlyFollowed = false;

    CheckBox checkBox;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_description_layout, null);

        Bundle bundle = getArguments();

        user_id = bundle.getInt(USERID_KEY);

        final String firstName = bundle.getString(ARG_FIRSTNAME);
        final String lastName = bundle.getString(ARG_LASTNAME);
        String profilePic = bundle.getString(ARG_PROFILE);
        String hometown = bundle.getString(ARG_HOMETOWN);
        final String birthday = bundle.getString(ARG_BIRTHDAY);
        String bio = bundle.getString(ARG_BIO);

        ImageView profilePicture = view.findViewById(R.id.image_dialog_profile);

        TextView firstText = view.findViewById(R.id.first_name_dialog_profile);
        TextView lastText = view.findViewById(R.id.last_name_dialog_profile);
        TextView hometownText = view.findViewById(R.id.hometown_name_dialog_profile);
        TextView bioText = view.findViewById(R.id.bio_dialog_profile_text);

        TextView birthdayText = view.findViewById(R.id.birthdate_name_dialog_profile);

        firstText.setText(firstName);
        lastText.setText(lastName);
        hometownText.setText(hometown);
        birthdayText.setText(birthday);
        bioText.setText(bio);

        checkBox = view.findViewById(R.id.checkbox_follow);

        int followedID = FollowTableModify.getFollowedID(firstName, lastName, birthday, ProfileDatabase.getInstance(getContext()));

        if (FollowTableModify.checkFollow(user_id, followedID, FollowerDatabase.getInstance(getContext()))) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    FollowerEntity entity = new FollowerEntity();
                    entity.setUser_id(user_id);
                    entity.setId_followed(FollowTableModify.getFollowedID(firstName, lastName, birthday, ProfileDatabase.getInstance(getContext())));

                    FollowerDatabase.getInstance(getContext()).followDAO().insertAll(entity);

                    for (FollowerEntity enttity : FollowerDatabase.getInstance(getContext()).followDAO().getAll()) {
                        Log.i("Database", "Current ID: " + Integer.toString(enttity.getUser_id()) + " Followed ID: " + Integer.toString(enttity.getId_followed()));
                    }

                } else {
                    FollowerDatabase.getInstance(getContext()).followDAO().delete(FollowTableModify.findEntity(user_id, firstName, lastName, birthday,
                            FollowerDatabase.getInstance(getContext()),
                            ProfileDatabase.getInstance(getContext())));

                    for (FollowerEntity enttity : FollowerDatabase.getInstance(getContext()).followDAO().getAll()) {
                        Log.i("Database", "Current ID: " + Integer.toString(enttity.getUser_id()) + " Followed ID: " + Integer.toString(enttity.getId_followed()));
                    }
                }
            }
        });



        File mPhotoFile;

        File picturesDir = getActivity().getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        mPhotoFile = new File(picturesDir, profilePic);

        Bitmap photo = getScaledBitmap(mPhotoFile.getPath(), 150, 200);

        profilePicture.setImageBitmap(photo);

        profilePicture.setScaleType(ImageButton.ScaleType.CENTER_CROP);

        return new AlertDialog.Builder(getActivity()).setView(view).setTitle("Information").setPositiveButton("Done", null).create();


    }

    //Source: Lecture Slides
    public static Bitmap getScaledBitmap(String path, int width, int height) {
        BitmapFactory.Options options =
                new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        int sampleSize = 1;
        if(srcHeight > height || srcWidth > width ) {
            if(srcWidth > srcHeight) {
                sampleSize = Math.round(srcHeight / height);
            }
            else {
                sampleSize = Math.round(srcWidth / width);
            }
        }
        BitmapFactory.Options scaledOptions =
                new BitmapFactory.Options();
        scaledOptions.inSampleSize = sampleSize;

        return BitmapFactory.decodeFile(path, scaledOptions);
    }
}
