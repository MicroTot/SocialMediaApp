package com.demo.csc214.socialmediaapp.model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.demo.csc214.socialmediaapp.model.DAO.UserDAO;
import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;



@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase{

    public abstract UserDAO userDAO();

    public static UserDatabase db;

    public static UserDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user-database-name").allowMainThreadQueries().build();
        }

        return db;
    }

}
