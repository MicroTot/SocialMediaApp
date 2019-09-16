package com.demo.csc214.socialmediaapp.controller;

import com.demo.csc214.socialmediaapp.model.Database.PostDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.PostEntity;
import com.demo.csc214.socialmediaapp.model.Post.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sailesh on 4/22/18.
 */

public class PostListOrganizer {

    public static List<Post> getListOfPosts(List<Integer> listFollowed, PostDatabase db) {
        List<Post> list = new LinkedList<>();
        for (PostEntity postEntity : db.postDAO().getAll()) {
            if (listFollowed.contains(postEntity.getUser_id())) {
                Post post = getPostFromEntity(postEntity);
                list.add(post);
            }
        }
        return list;
    }

    public static Post getPostFromEntity(PostEntity postEntity) {
        Post post = new Post(postEntity.getUser_id(), postEntity.getPostURL(), postEntity.getImagePath(), postEntity.getText(), postEntity.getPostDate());
        return post;
    }

    //Source: https://stackoverflow.com/questions/5301226/convert-string-to-calendar-object-in-java
    public static List<Post> sortList(List<Post> list) {
        Collections.sort(list, new Comparator<Post>() {
            public int compare(Post o1, Post o2) {

                Calendar cal1 = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
                try {
                    cal1.setTime(sdf1.parse(o1.getPostDate()));// all done
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar cal2 = Calendar.getInstance();
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
                try {
                    cal2.setTime(sdf2.parse(o2.getPostDate()));// all done
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (cal1.before(cal2)) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });

        return list;
    }

}
