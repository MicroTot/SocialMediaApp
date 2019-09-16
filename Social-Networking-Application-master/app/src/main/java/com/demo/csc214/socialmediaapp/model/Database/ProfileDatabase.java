package com.demo.csc214.socialmediaapp.model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.demo.csc214.socialmediaapp.model.DAO.PostDAO;
import com.demo.csc214.socialmediaapp.model.DAO.ProfileDao;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;



@Database(entities = {ProfileEntity.class}, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {

    public abstract ProfileDao profileDao();

    public static ProfileDatabase db;

    public static ProfileDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), ProfileDatabase.class, "profile-database-name").allowMainThreadQueries().build();
        }

        return db;
    }
}
