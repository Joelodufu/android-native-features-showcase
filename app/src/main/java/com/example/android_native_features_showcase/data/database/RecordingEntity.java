package com.example.android_native_features_showcase.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "recordings")
public class RecordingEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "file_path")
    private String filePath;

    @ColumnInfo(name = "file_name")
    private String fileName;

    @ColumnInfo(name = "duration")
    private long duration;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "is_uploaded")
    private boolean isUploaded;

    @ColumnInfo(name = "cloudinary_url")
    private String cloudinaryUrl;

    // Constructors
    public RecordingEntity() {}

    public RecordingEntity(String filePath, String fileName, long duration, long timestamp) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.duration = duration;
        this.timestamp = timestamp;
        this.isUploaded = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public long getDuration() { return duration; }
    public void setDuration(long duration) { this.duration = duration; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public boolean isUploaded() { return isUploaded; }
    public void setUploaded(boolean uploaded) { isUploaded = uploaded; }

    public String getCloudinaryUrl() { return cloudinaryUrl; }
    public void setCloudinaryUrl(String cloudinaryUrl) { this.cloudinaryUrl = cloudinaryUrl; }
}
