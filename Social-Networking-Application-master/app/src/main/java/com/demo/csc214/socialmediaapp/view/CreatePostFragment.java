package com.demo.csc214.socialmediaapp.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.CheckCreatePostInputs;
import com.demo.csc214.socialmediaapp.controller.PostListOrganizer;
import com.demo.csc214.socialmediaapp.controller.PostQuery;
import com.demo.csc214.socialmediaapp.model.Database.PostDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.PostEntity;
import com.demo.csc214.socialmediaapp.model.Post.Post;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Sailesh on 4/20/18.
 */

public class CreatePostFragment extends Fragment{

    View view;
    ImageButton mPicture;
    EditText postText;
    EditText urlPost;
    Button mSubmit;

    int user_id;

    String currentImageFile = "standard_pig.jpg";

    File mPhotoFile;

    public final String USERID_KEY = "USER_ID_KEY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            user_id = getArguments().getInt(USERID_KEY, PostQuery.currentID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_post_layout, container, false);

        mPicture = view.findViewById(R.id.image_button_post);
        postText = view.findViewById(R.id.post_text);
        urlPost = view.findViewById(R.id.url_field);
        mSubmit = view.findViewById(R.id.submit_post_button);


        mPicture.setOnClickListener(new View.OnClickListener() {
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

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckCreatePostInputs.checkInput(postText, urlPost)) {
                    Toast.makeText(getContext(), "At Least One Field Empty", Toast.LENGTH_SHORT).show();
                } //else if (!CheckCreatePostInputs.validURL(urlPost)) {
                    //Toast.makeText(getContext(), "Invalid URL!", Toast.LENGTH_SHORT).show();
                else {
                    PostEntity post = new PostEntity();
                    post.setImagePath(currentImageFile);
                    post.setUser_id(user_id);
                    post.setText(postText.getText().toString());
                    post.setPostURL(urlPost.getText().toString());


                    //Source: https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
                    Date c = Calendar.getInstance().getTime();

                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String formattedDate = df.format(c);

                    post.setPostDate(formattedDate);

                    PostDatabase.getInstance(getContext()).postDAO().insertAll(post);

                    for (PostEntity postEntity : PostDatabase.getInstance(getContext()).postDAO().getAll()) {
                        Post p = PostListOrganizer.getPostFromEntity(postEntity);
                        Log.i("Current Post", p.toString());
                    }

                    Log.i("Size of Database", Integer.toString(PostDatabase.getInstance(getContext()).postDAO().getAll().size()));

                    Toast.makeText(getContext(), "Posted!", Toast.LENGTH_SHORT).show();

                    Fragment fragment = new NewsFeedFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt(USERID_KEY, user_id);
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            }
        });


        return view;
    }

    //Source: Lecture Slides
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo = getScaledBitmap(mPhotoFile.getPath(), mPicture.getWidth(), mPicture.getHeight());

        mPicture.setImageBitmap(photo);

        mPicture.setScaleType(ImageButton.ScaleType.CENTER_CROP);
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
