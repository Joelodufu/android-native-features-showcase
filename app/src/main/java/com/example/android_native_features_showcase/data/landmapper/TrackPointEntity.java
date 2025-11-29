package com.example.android_native_features_showcase.data.landmapper;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
    tableName = "track_points",
    foreignKeys = @ForeignKey(
        entity = LandTrackEntity.class,
        parentColumns = "id",
        childColumns = "landTrackId",
        onDelete = CASCADE
    ),
    indices = {@Index(value = "landTrackId")}
)
public class TrackPointEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "landTrackId")
    public long landTrackId;

    @ColumnInfo(name = "latitude")
    public double latitude;

    @ColumnInfo(name = "longitude")
    public double longitude;

    @ColumnInfo(name = "altitude")
    public double altitude;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "accuracy")
    public float accuracy;

    public TrackPointEntity(long landTrackId, double latitude, double longitude, double altitude, long timestamp, float accuracy) {
        this.landTrackId = landTrackId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.timestamp = timestamp;
        this.accuracy = accuracy;
    }

    // Getters and setters can be added here if needed
}
