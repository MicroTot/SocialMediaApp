package com.demo.csc214.socialmediaapp.model.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sailesh on 4/18/18.
 */

@Entity
public class PostEntity {


    @PrimaryKey(autoGenerate = true)
    private int post_id;

    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "postURL")
    private String postURL;

    @ColumnInfo(name = "imagePath")
    private String imagePath;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "postDate")
    private String postDate;


    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

}
