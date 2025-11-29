package com.example.android_native_features_showcase.landmapper.domain;

public interface LocationRepository {
    // Define methods for location data handling
    void saveLocation(double latitude, double longitude);
    double[] getLastKnownLocation();
}