package com.demo.csc214.socialmediaapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.FollowTableModify;
import com.demo.csc214.socialmediaapp.model.Database.FollowerDatabase;
import com.demo.csc214.socialmediaapp.model.Profile.Profile;

import java.io.File;
import java.util.List;

/**
 * Created by Sailesh on 4/20/18.
 */

public class ProfileAdapter extends ArrayAdapter<Profile> {

    int user_id;

    public ProfileAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ProfileAdapter(Context context, int resource,  List<Profile> items, int user_id) {
        super(context, resource, items);
        this.user_id = user_id;
    }

    public View getView(final int position, View layout, ViewGroup parent) {
        if (layout == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            layout = inflater.inflate(R.layout.profile_list_adapter_view, parent, false);
        }

        notifyDataSetChanged();

        ImageView profilePicture = layout.findViewById(R.id.photo_user_list);
        TextView fullname = layout.findViewById(R.id.profile_list_name_view);
        ImageView followBox = layout.findViewById(R.id.follow_box);


        Profile profile = getItem(position);

        if (FollowTableModify.checkFollow(user_id, profile.getUser_id(),FollowerDatabase.getInstance(getContext()))) {
            followBox.setImageResource(R.drawable.ic_user_list);
        } else {
            followBox.setImageResource(R.drawable.ic_edit_profile);
        }

            String currentImageFile = profile.getProfilePhoto();

        File mPhotoFile;

        File picturesDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        mPhotoFile = new File(picturesDir, currentImageFile);
        Bitmap photo = getScaledBitmap(mPhotoFile.getPath(), 45, 60);

        profilePicture.setImageBitmap(photo);
        profilePicture.setScaleType(ImageButton.ScaleType.CENTER_CROP);

        fullname.setText(profile.getFirstName() + " " + profile.getLastName());





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
