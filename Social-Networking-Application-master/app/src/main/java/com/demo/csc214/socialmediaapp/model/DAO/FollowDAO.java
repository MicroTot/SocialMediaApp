package com.demo.csc214.socialmediaapp.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.demo.csc214.socialmediaapp.model.Entities.FollowerEntity;
import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;

import java.util.List;

/**
 * Created by Sailesh on 4/18/18.
 */

@Dao
public interface FollowDAO {

    @Query("SELECT * FROM FollowerEntity")
    List<FollowerEntity> getAll();

    @Query("SELECT * FROM FollowerEntity WHERE user_id IN (:userIds)")
    List<FollowerEntity> loadALLByIds(int[] userIds);

    @Query("SELECT * FROM FollowerEntity WHERE user_id LIKE :userID")
    FollowerEntity getByID(int userID);


    @Insert
    void insertAll(FollowerEntity... followerEntities);


    @Delete
    void delete(FollowerEntity followerEntity);

    //Deletes Table
    @Query("DELETE FROM FollowerEntity")
    void nukeTable();


}
