// Implementation of LocationRepositoryImpl with FusedLocationProvider
package com.example.android_native_features_showcase.landmapper.data;

import android.annotation.SuppressLint;
import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import android.location.Location;

public class LocationRepositoryImpl {

    private final FusedLocationProviderClient fusedLocationClient;

    public LocationRepositoryImpl(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    public Task<Location> getLastKnownLocation() {
        return fusedLocationClient.getLastLocation();
    }
}
