package com.example.android_native_features_showcase.domain.model;

public class Recording {
    private String fileName;
    private String filePath;
    private long timestamp;
    private String duration;

    public Recording(String fileName, String filePath, long timestamp, String duration) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.timestamp = timestamp;
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
