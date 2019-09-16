package com.demo.csc214.socialmediaapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.FollowTableModify;
import com.demo.csc214.socialmediaapp.controller.PostQuery;
import com.demo.csc214.socialmediaapp.model.Database.FollowerDatabase;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Post.Post;
import com.demo.csc214.socialmediaapp.model.Profile.Profile;

import java.io.File;
import java.util.List;

/**
 * Created by Sailesh on 4/22/18.
 */

public class PostAdapter extends ArrayAdapter<Post> {

    int user_id;


    public PostAdapter(Context context, int resource, List<Post> items, int user_id) {
        super(context, resource, items);
        this.user_id = user_id;
    }

    public View getView(final int position, View layout, ViewGroup parent) {
        if (layout == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            layout = inflater.inflate(R.layout.post_list_view, parent, false);
        }

        TextView dateText = layout.findViewById(R.id.date_in_post);
        ImageView postPicture = layout.findViewById(R.id.post_list_id);

        TextView firstNameText = layout.findViewById(R.id.first_name_post_list);
        TextView lastNameText = layout.findViewById(R.id.last_name_post_list);
        TextView urlText = layout.findViewById(R.id.url_text_post);

        Post post = getItem(position);

        String currentImageFile = post.getImagePath();

        File mPhotoFile;

        File picturesDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mPhotoFile = new File(picturesDir, currentImageFile);
        Bitmap photo = getScaledBitmap(mPhotoFile.getPath(), 75, 100);

        postPicture.setImageBitmap(photo);
        postPicture.setScaleType(ImageButton.ScaleType.CENTER_CROP);

        dateText.setText(post.getPostDate());
        urlText.setText(post.getPostURL());

        String[] args = PostQuery.getFirstLast(post.getUser_id(), ProfileDatabase.getInstance(getContext()));

        firstNameText.setText(args[0]);
        lastNameText.setText(args[1]);

        return layout;

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
