package com.demo.csc214.socialmediaapp.model.Profile;

/**
 * Created by Sailesh on 4/18/18.
 */

public class Profile {

    private int user_id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String profilePhoto;
    private String hometown;
    private String bio;

    public Profile(int user_id, String firstName, String lastName, String birthDate, String profilePhoto, String hometown, String bio) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.profilePhoto = profilePhoto;
        this.hometown = hometown;
        this.bio = bio;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }



    public boolean equals(Profile profile) {
        if (user_id == profile.getUser_id() && firstName.equals(profile.getFirstName()) && lastName.equals(profile.getLastName()) && birthDate.equals(profile.getBirthDate()) && profilePhoto.equals(profile.getProfilePhoto()) && hometown.equals(profile.getHometown()) && bio.equals(profile.getBio())) {
            return true;
        }
        return false;
    }



}
