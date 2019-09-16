package com.demo.csc214.socialmediaapp.controller;

import com.demo.csc214.socialmediaapp.model.Database.FollowerDatabase;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.FollowerEntity;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sailesh on 4/22/18.
 */

public class FollowTableModify {

    public static FollowerEntity findEntity(int user_id, String firstName, String lastName, String dateOfBirth, FollowerDatabase fdb, ProfileDatabase pdb) {
        int followedID = 0;
        for (ProfileEntity entity : pdb.profileDao().getAll()) {
            if (entity.getFirstName().equals(firstName) && entity.getLastName().equals(lastName) && entity.getBirthDate().equals(dateOfBirth)) {
                followedID = entity.getUser_id();
            }
        }

        for (FollowerEntity entity : fdb.followDAO().getAll()) {
            if (entity.getUser_id() == user_id && entity.getId_followed() == followedID) {
                return entity;
            }
        }

        return null;
    }

    public static int getFollowedID(String firstName, String lastName, String dateOfBirth, ProfileDatabase pdb) {
        int followedID = 0;
        for (ProfileEntity entity : pdb.profileDao().getAll()) {
            if (entity.getFirstName().equals(firstName) && entity.getLastName().equals(lastName) && entity.getBirthDate().equals(dateOfBirth)) {
                followedID = entity.getUser_id();
            }
        }
        return followedID;
    }

    public static boolean checkFollow(int user_id, int followedId, FollowerDatabase db) {
        for (FollowerEntity followerEntity : db.followDAO().getAll()) {
            if (followerEntity.getUser_id() == user_id && followerEntity.getId_followed() == followedId) {
                return true;
            }
        }

        return false;
    }

    public static List<Integer> getListOfFollowers(int user_id, FollowerDatabase db) {
        List<Integer> list = new LinkedList<>();
        for (FollowerEntity entity : db.followDAO().getAll()) {
            if (entity.getUser_id() == user_id) {
                list.add(entity.getId_followed());
            }
        }

        return list;
    }
}
