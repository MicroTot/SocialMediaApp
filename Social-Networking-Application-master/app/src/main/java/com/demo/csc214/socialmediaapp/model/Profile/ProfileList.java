package com.demo.csc214.socialmediaapp.model.Profile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sailesh on 4/18/18.
 */

public class ProfileList {
    public List<Profile> profileList;

    public ProfileList() {
        profileList = new LinkedList<>();
    }

    public void addProfile(Profile profile) {
        profileList.add(profile);
    }

    public void deleteProfile(Profile profile) {
        for (Profile p : profileList) {
            if (p.equals(profile)) {
                profileList.remove(p);
            }
        }
    }

    public void addProfile(int user_id, String firstName, String lastName, String birthDate, String profilePhoto, String hometown, String bio) {
        Profile p = new Profile( user_id,  firstName,  lastName,  birthDate,  profilePhoto,  hometown,  bio);
        profileList.add(p);
    }

    public void deleteProfile(int user_id, String firstName, String lastName, String birthDate, String profilePhoto, String hometown, String bio) {
        Profile profile = new Profile( user_id,  firstName,  lastName,  birthDate,  profilePhoto,  hometown,  bio);
        for (Profile p : profileList) {
            if (p.equals(profile)) {
                profileList.remove(p);
            }
        }
    }
}
