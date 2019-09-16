package com.demo.csc214.socialmediaapp.controller;

import com.demo.csc214.socialmediaapp.model.Database.PostDatabase;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.PostEntity;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;
import com.demo.csc214.socialmediaapp.model.Post.Post;
import com.demo.csc214.socialmediaapp.model.Profile.Profile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sailesh on 4/20/18.
 */

public class DatabaseQueriesHandler {

    public static String[] getValuesProfileEdit(int user_id, ProfileDatabase db) {
        String[] args = new String[6];
        for (ProfileEntity profileEntity : db.profileDao().getAll()) {
            if (profileEntity.getUser_id() == user_id) {
                args[0] = profileEntity.getProfilePhoto();
                args[1] = profileEntity.getFirstName();
                args[2] = profileEntity.getLastName();
                args[3] = profileEntity.getBirthDate();
                args[4] = profileEntity.getHometown();
                args[5] = profileEntity.getBio();
            }
        }
        return args;
    }

    public static ProfileEntity getProfileEntity(int user_id, ProfileDatabase db) {
        for (ProfileEntity profileEntity : db.profileDao().getAll()) {
            if (profileEntity.getUser_id() == user_id) {
               return profileEntity;
            }
        }
        return null;
    }

    public static List<Profile> getListOfProfile(ProfileDatabase db) {
        List<Profile> list = new LinkedList<>();
        for (ProfileEntity entity : db.profileDao().getAll()) {
            Profile profile = new Profile(entity.getUser_id(), entity.getFirstName(), entity.getLastName(), entity.getBirthDate(), entity.getProfilePhoto(), entity.getHometown(), entity.getBio());
            list.add(profile);
        }

        return list;

    }

}
