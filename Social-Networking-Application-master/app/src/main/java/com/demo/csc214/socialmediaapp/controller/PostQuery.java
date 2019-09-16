package com.demo.csc214.socialmediaapp.controller;

import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;

/**
 * Created by Sailesh on 4/22/18.
 */

public class PostQuery {

    public static int currentID;

    public static String[] getFirstLast(int user_id, ProfileDatabase db) {
        String[] args = new String[2];
        for (ProfileEntity profileEntity : db.profileDao().getAll()) {
            if (profileEntity.getUser_id() == user_id) {
                args[0] = profileEntity.getFirstName();
                args[1] = profileEntity.getLastName();
            }
        }
        return args;
    }
}
