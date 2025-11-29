package com.example.android_native_features_showcase.domain.landmapper;

import java.util.List;
import java.time.Instant;

public class LandTrack {
    private String id;
    private String name;
    private Instant timestamp;
    private List<TrackPoint> trackPoints;

    public LandTrack(String id, String name, Instant timestamp, List<TrackPoint> trackPoints) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.trackPoints = trackPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public static class TrackPoint {
        private double latitude;
        private double longitude;

        public TrackPoint(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
