package com.demo.csc214.socialmediaapp.model.Post;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sailesh on 4/17/18.
 */

public class PostList {

    public List<Post> postList;

    public PostList() {
        postList = new LinkedList<>();
    }

    public void addPost(Post post) {
        postList.add(post);
    }

    public void addPost(int user_id, String postURL, String imagePath, String text, String postDate) {
        Post post = new Post(user_id, postURL, imagePath, text, postDate);
        postList.add(post);
    }

    public void deletePost(Post post) {
        for (Post p : postList) {
            if (p.equals(post)) {
                postList.remove(p);
            }
        }
    }

    public void deletePost(int user_id, String postURL, String imagePath, String text, String postDate) {
        Post post = new Post(user_id, postURL, imagePath, text, postDate);
        for (Post p : postList) {
            if (p.equals(post)) {
                postList.remove(p);
            }
        }
    }
}
