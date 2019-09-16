package com.demo.csc214.socialmediaapp.model.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sailesh on 4/18/18.
 */

@Entity
public class FollowerEntity {

    @PrimaryKey(autoGenerate = true)
    private int follower_id;

    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "id_followed")
    private int id_followed;

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

    public int getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }
}
