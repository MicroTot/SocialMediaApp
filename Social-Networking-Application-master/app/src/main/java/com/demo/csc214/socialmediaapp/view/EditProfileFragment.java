package com.demo.csc214.socialmediaapp.view;

import android.support.v4.app.Fragment;
import android.arch.persistence.room.Database;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.CheckCreateProfileElements;
import com.demo.csc214.socialmediaapp.controller.DatabaseQueriesHandler;
import com.demo.csc214.socialmediaapp.controller.PostQuery;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;
import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Sailesh on 4/20/18.
 */

public class EditProfileFragment extends Fragment{

    View view;

    ImageButton mProfilePicture;

    EditText mFirstNameBox;
    EditText mLastNameBox;
    EditText mBirthdateBox;
    EditText mHometownBox;

    EditText mBio;

    Button mCreateButton;

    String currentImageFile;

    File mPhotoFile;

    public final String USERID_KEY = "USER_ID_KEY";

    public int user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            user_id = getArguments().getInt(USERID_KEY, PostQuery.currentID);
            Log.i("Current User ID: ", Integer.toString(user_id));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        view = inflater.inflate(R.layout.profile_edit_layout, container, false);

        mProfilePicture = view.findViewById(R.id.image_submit_profile);

        mProfilePicture.setMinimumWidth(150);
        mProfilePicture.setMaxWidth(150);

        mProfilePicture.setMinimumWidth(200);
        mProfilePicture.setMaxWidth(200);

        mFirstNameBox = view.findViewById(R.id.first_name_submit_profile);
        mLastNameBox = view.findViewById(R.id.last_name_submit_profile);
        mBirthdateBox = view.findViewById(R.id.birthdate_name_submit_profile);
        mHometownBox = view.findViewById(R.id.hometown_name_submit_profile);

        mBio = view.findViewById(R.id.bio_submit_profile_text);

        mCreateButton = view.findViewById(R.id.submit_profile_button_activity);

        String[] values = DatabaseQueriesHandler.getValuesProfileEdit(user_id, ProfileDatabase.getInstance(getContext()));

        for (ProfileEntity entity : ProfileDatabase.getInstance(getContext()).profileDao().getAll()) {
            Log.i("Current Database Profile", entity.getUser_id() + " " + entity.getFirstName() + " " + entity.getLastName());
        }

        currentImageFile = values[0];
        Log.i("Image: ", Arrays.toString(values));
        File picturesDir = getActivity().getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mPhotoFile = new File(picturesDir, currentImageFile);
        Bitmap photo = getScaledBitmap(mPhotoFile.getPath(), 150, 200);

        mProfilePicture.setImageBitmap(photo);
        mProfilePicture.setScaleType(ImageButton.ScaleType.CENTER_CROP);

        mFirstNameBox.setText(values[1]);
        mLastNameBox.setText(values[2]);
        mBirthdateBox.setText(values[3]);
        mHometownBox.setText(values[4]);
        mBio.setText(values[5]);

        mProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                currentImageFile = "IMG_" + UUID.randomUUID().toString() + ".jpg";

                File picturesDir = getActivity().getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                mPhotoFile = new File(picturesDir, currentImageFile);

                Uri photoUri = Uri.fromFile(mPhotoFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                startActivityForResult(intent, 0);
            }
        });




        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckCreateProfileElements.checkEmptyFields(mFirstNameBox, mLastNameBox, mBirthdateBox, mHometownBox, mBio)) {
                    Toast.makeText(getContext(), "At Least One Field Empty", Toast.LENGTH_SHORT).show();
                } else if (!CheckCreateProfileElements.checkDateValid(mBirthdateBox)) {
                    Toast.makeText(getContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                } else {
                    ProfileEntity profileEntity = DatabaseQueriesHandler.getProfileEntity(user_id, ProfileDatabase.getInstance(getContext()));
                    ProfileDatabase.getInstance(getContext()).profileDao().delete(profileEntity);


                    ProfileEntity insertEntity = new ProfileEntity();

                    insertEntity.setUser_id(user_id);

                    insertEntity.setProfilePhoto(currentImageFile);
                    insertEntity.setFirstName(mFirstNameBox.getText().toString());
                    insertEntity.setLastName(mLastNameBox.getText().toString());
                    insertEntity.setBirthDate(mBirthdateBox.getText().toString());
                    insertEntity.setHometown(mHometownBox.getText().toString());
                    insertEntity.setBio(mBio.getText().toString());

                    ProfileDatabase.getInstance(getContext()).profileDao().insertAll(insertEntity);

                    Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    //Source: Lecture Slides
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo = getScaledBitmap(mPhotoFile.getPath(), mProfilePicture.getWidth(), mProfilePicture.getHeight());

        mProfilePicture.setImageBitmap(photo);

        mProfilePicture.setScaleType(ImageButton.ScaleType.CENTER_CROP);
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
