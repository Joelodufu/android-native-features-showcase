package com.example.android_native_features_showcase.data.landmapper;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.android_native_features_showcase.data.landmapper.LandTrack;

@Dao
public interface LandTrackDao {

    @Insert
    void insert(LandTrack landTrack);

    @Update
    void update(LandTrack landTrack);

    @Delete
    void delete(LandTrack landTrack);

    @Query("SELECT * FROM landtrack WHERE id = :id")
    LandTrack getById(int id);

    @Query("SELECT * FROM landtrack")
    List<LandTrack> getAll();

}