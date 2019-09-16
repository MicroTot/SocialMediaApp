package com.demo.csc214.socialmediaapp.model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.demo.csc214.socialmediaapp.model.Entities.PostEntity;

import java.util.List;

/**
 * Created by Sailesh on 4/18/18.
 */

@Dao
public interface PostDAO {

    @Query("SELECT * FROM PostEntity")
    List<PostEntity> getAll();

    @Query("SELECT * FROM PostEntity WHERE post_id IN (:postIds)")
    List<PostEntity> loadALLByIds(int[] postIds);

    @Query("SELECT * FROM PostEntity WHERE post_id LIKE :postID")
    PostEntity getByID(int postID);

    @Query("SELECT * FROM PostEntity WHERE user_id LIKE :userID")
    List<PostEntity> findByUserID(int userID);

    @Insert
    void insertAll(PostEntity... postEntities);


    @Delete
    void delete(PostEntity postEntity);

    //Deletes Table
    @Query("DELETE FROM PostEntity")
    void nukeTable();
}
