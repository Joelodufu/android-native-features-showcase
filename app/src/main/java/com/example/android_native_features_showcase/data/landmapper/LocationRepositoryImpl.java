package com.example.android_native_features_showcase.data.landmapper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.example.android_native_features_showcase.data.landmapper.LocationRepository;

public class LocationRepositoryImpl implements LocationRepository {

    private final FusedLocationProviderClient fusedLocationClient;
    private final Context context;
    private LocationCallback locationCallback;

    public LocationRepositoryImpl(@NonNull Context context) {
        this.context = context.getApplicationContext();
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.context);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void getCurrentLocation(@NonNull final LocationResultCallback callback) {
        if (!hasLocationPermission()) {
            callback.onLocationResult(null);
            return;
        }
        fusedLocationClient.getLastLocation()
            .addOnSuccessListener(location -> callback.onLocationResult(location))
            .addOnFailureListener(e -> callback.onLocationResult(null));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void startLocationUpdates(@NonNull LocationRequest locationRequest, @NonNull LocationResultCallback callback) {
        if (!hasLocationPermission()) {
            callback.onLocationResult(null);
            return;
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                callback.onLocationResult(locationResult.getLastLocation());
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void stopLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
            locationCallback = null;
        }
    }

    @Override
    public boolean hasLocationPermission() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public interface LocationResultCallback {
        void onLocationResult(Location location);
    }
}
