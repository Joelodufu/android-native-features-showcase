package com.example.android_native_features_showcase.data.landmapper;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Ignore;

@Entity(tableName = "land_tracks")
public class LandTrackEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    // Assuming TrackPointEntity is another entity class representing points in the track
    // We can represent the relationship in Room using @Relation in a POJO class, but here
    // we just keep a foreign key or a list of points in a separate class.

    public LandTrackEntity(long id, String name, long timestamp) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
    }

    @Ignore
    public LandTrackEntity(String name, long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public LandTrackEntity() {
    }
}
