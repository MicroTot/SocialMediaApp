package com.demo.csc214.socialmediaapp.model.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.demo.csc214.socialmediaapp.model.DAO.PostDAO;
import com.demo.csc214.socialmediaapp.model.Entities.PostEntity;



@Database(entities = {PostEntity.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {

    public abstract PostDAO postDAO();

    public static PostDatabase db;

    public static PostDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), PostDatabase.class, "post-database-name").allowMainThreadQueries().build();
        }

        return db;
    }


}
