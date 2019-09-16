package com.demo.csc214.socialmediaapp.model.Follower;

/**
 * Created by Sailesh on 4/17/18.
 */

public class FollowInfo {

    private int user_id;
    private int id_followed;

    public FollowInfo(int user_id, int id_followed) {
        this.user_id = user_id;
        this.id_followed = id_followed;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId_followed() {
        return id_followed;
    }

    public void setId_followed(int id_followed) {
        this.id_followed = id_followed;
    }

    public boolean equals(FollowInfo followInfo) {
        if (user_id == followInfo.getUser_id() && id_followed == followInfo.getId_followed()) {
            return true;
        }
        return false;
    }

}
