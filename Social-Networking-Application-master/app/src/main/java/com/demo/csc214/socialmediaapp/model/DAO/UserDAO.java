package com.demo.csc214.socialmediaapp.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;

import java.util.List;

/**
 * Created by Sailesh on 4/18/18.
 */

@Dao
public interface UserDAO {

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAll();


    @Query("SELECT * FROM UserEntity WHERE user_id LIKE :userID")
    UserEntity getByID(int userID);


    @Insert
    void insertAll(UserEntity... userEntities);


    @Delete
    void delete(UserEntity userEntity);

    //Deletes Table
    @Query("DELETE FROM UserEntity")
    void nukeTable();
}
