package com.example.android_native_features_showcase.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_native_features_showcase.data.entities.LandTrackEntity;
import com.example.android_native_features_showcase.data.entities.TrackPointEntity;
import com.example.android_native_features_showcase.data.dao.LandTrackDao;

@Database(entities = {LandTrackEntity.class, TrackPointEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LandTrackDao landTrackDao();
}
