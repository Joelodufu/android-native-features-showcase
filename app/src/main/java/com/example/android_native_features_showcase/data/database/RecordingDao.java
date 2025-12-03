package com.example.android_native_features_showcase.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordingDao {
    @Query("SELECT * FROM recordings ORDER BY timestamp DESC")
    List<RecordingEntity> getAllRecordings();

    @Query("SELECT * FROM recordings WHERE is_uploaded = 0")
    List<RecordingEntity> getPendingUploads();

    @Insert
    void insert(RecordingEntity recording);

    @Update
    void update(RecordingEntity recording);

    @Delete
    void delete(RecordingEntity recording);
    
    @Query("UPDATE recordings SET is_uploaded = 1, cloudinary_url = :url WHERE id = :id")
    void markAsUploaded(int id, String url);
}