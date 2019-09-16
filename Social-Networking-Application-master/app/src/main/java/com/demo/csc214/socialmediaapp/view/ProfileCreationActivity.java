package com.demo.csc214.socialmediaapp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.CheckCreateProfileElements;
import com.demo.csc214.socialmediaapp.controller.PostQuery;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;
import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;
import com.demo.csc214.socialmediaapp.model.Profile.Profile;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class ProfileCreationActivity extends AppCompatActivity {

    public final String USERID_KEY = "USER_ID_KEY";

    ImageButton mProfilePicture;

    EditText mFirstNameBox;
    EditText mLastNameBox;
    EditText mBirthdateBox;
    EditText mHometownBox;

    EditText mBio;

    Button mCreateButton;

    String currentImageFile = "standard_pig.jpg";

    File mPhotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Intent profileID = getIntent();

        final int user_id = profileID.getIntExtra(USERID_KEY, 0);

        Log.i("Current ID Profile Create:", Integer.toString(user_id));

        mProfilePicture = findViewById(R.id.image_create_profile);

        mFirstNameBox = findViewById(R.id.first_name_create_profile);
        mLastNameBox = findViewById(R.id.last_name_create_profile);
        mBirthdateBox = findViewById(R.id.birthdate_name_create_profile);
        mHometownBox = findViewById(R.id.hometown_name_create_profile);

        mBio = findViewById(R.id.bio_create_profile_text);

        mCreateButton = findViewById(R.id.create_profile_button_activity);

        //Source: Powerpoint Slides
        mProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                currentImageFile = "IMG_" + UUID.randomUUID().toString() + ".jpg";

                File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

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
                    Toast.makeText(getApplicationContext(), "At Least One Field Empty", Toast.LENGTH_SHORT).show();
                } //else if (!CheckCreateProfileElements.checkImageChanged(mProfilePicture)) {
                    //Toast.makeText(getApplicationContext(), "Profile Picture Unchanged", Toast.LENGTH_SHORT).show();
                //}
                else if (!CheckCreateProfileElements.checkDateValid(mBirthdateBox)) {
                    Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                } else {
                    ProfileEntity profileEntity = new ProfileEntity();

                    profileEntity.setUser_id(user_id);
                    profileEntity.setProfilePhoto(currentImageFile);


                    String[] values = CheckCreateProfileElements.getValuesFromBoxes(mFirstNameBox, mLastNameBox, mBirthdateBox, mHometownBox, mBio);
                    profileEntity.setFirstName(values[0]);
                    profileEntity.setLastName(values[1]);
                    profileEntity.setBirthDate(values[2]);
                    profileEntity.setHometown(values[3]);
                    profileEntity.setBio(values[4]);
                    profileEntity.setTotalFollowers(0);

                    ProfileDatabase.getInstance(getApplicationContext()).profileDao().insertAll(profileEntity);

                    Intent myIntent = new Intent(ProfileCreationActivity.this, SocialActivity.class);
                    myIntent.putExtra(USERID_KEY, user_id);

                    PostQuery.currentID = user_id;

                    Toast.makeText(getApplicationContext(), "Profile Created!", Toast.LENGTH_SHORT).show();

                    startActivity(myIntent);

                }
            }
        });

    }

    //Source: Lecture Slides
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
