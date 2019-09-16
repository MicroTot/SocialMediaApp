package com.demo.csc214.socialmediaapp.model.Post;

import java.net.URL;
import java.util.Date;

/**
 * Created by Sailesh on 4/17/18.
 */

public class Post {

    private int user_id;
    private String postURL;
    private String imagePath;
    private String text;
    private String postDate;

    public Post(int user_id, String postURL, String imagePath, String text, String postDate) {
        this.user_id = user_id;
        this.postURL = postURL;
        this.imagePath = imagePath;
        this.text = text;
        this.postDate = postDate;
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

    public boolean equals(Post post) {
        if (user_id == post.getUser_id() && postURL.equals(post.getPostURL()) && imagePath.equals(post.getImagePath()) && text.equals(post.getText()) && postDate.equals(post.getPostDate())) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "ID: " + user_id + "PostURL: " + postURL + "Image: " + imagePath + "Post Date: " + postDate + "Text: " + text;
    }

}
