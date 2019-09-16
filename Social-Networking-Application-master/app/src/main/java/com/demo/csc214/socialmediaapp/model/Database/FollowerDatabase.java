package com.demo.csc214.socialmediaapp.model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.demo.csc214.socialmediaapp.model.DAO.FollowDAO;
import com.demo.csc214.socialmediaapp.model.Entities.FollowerEntity;


@Database(entities = {FollowerEntity.class}, version = 1)
public abstract class FollowerDatabase extends RoomDatabase {

    public abstract FollowDAO followDAO();

    public static FollowerDatabase db;

    public static FollowerDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), FollowerDatabase.class, "follower-database-name").allowMainThreadQueries().build();
        }

        return db;
    }

}
