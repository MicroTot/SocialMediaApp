package com.demo.csc214.socialmediaapp.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.demo.csc214.socialmediaapp.model.Entities.ProfileEntity;

import java.util.List;

/**
 * Created by Sailesh on 4/18/18.
 */

@Dao
public interface ProfileDao {

    @Query("SELECT * FROM ProfileEntity")
    List<ProfileEntity> getAll();

    @Query("SELECT * FROM ProfileEntity WHERE user_id IN (:userIds)")
    List<ProfileEntity> loadALLByIds(int[] userIds);

    @Query("SELECT * FROM ProfileEntity WHERE user_id LIKE :userID")
    ProfileEntity getByID(int userID);

    @Query("SELECT * FROM ProfileEntity WHERE lastName LIKE :lastName AND " + "birthDate LIKE :birthDate LIMIT 1")
    ProfileEntity findByNameDOB(String lastName, String birthDate);

    @Insert
    void insertAll(ProfileEntity... profileEntities);


    @Delete
    void delete(ProfileEntity profileEntity);

    //Deletes Table
    @Query("DELETE FROM ProfileEntity")
    void nukeTable();
}
