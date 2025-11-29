package com.example.android_native_features_showcase.landmapper.domain;

public class TrackPoint {
    private final double latitude;
    private final double longitude;
    private final long timestamp;

    public TrackPoint(double latitude, double longitude, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }
}
