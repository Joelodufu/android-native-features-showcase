package com.example.android_native_features_showcase.data.landmapper;

import android.location.Location;

public interface LocationRepository {

    // Get the current location
    Location getCurrentLocation();

    // Start location updates
    void startLocationUpdates(LocationCallback callback);

    // Stop location updates
    void stopLocationUpdates();

    // Check if location permissions are granted
    boolean hasLocationPermission();

    interface LocationCallback {
        void onLocationUpdated(Location location);
    }
}
