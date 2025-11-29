package com.example.android_native_features_showcase.presentation.landmapper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_native_features_showcase.data.repository.LocationRepository;
import com.example.android_native_features_showcase.data.repository.TrackRepository;
import com.example.android_native_features_showcase.model.Location;
import com.example.android_native_features_showcase.model.TrackPoint;

import java.util.List;

public class LandMapperViewModel extends ViewModel {

    private final TrackRepository trackRepository;
    private final LocationRepository locationRepository;

    private final MutableLiveData<Boolean> isRecording = new MutableLiveData<>(false);
    private final MutableLiveData<Location> currentLocation = new MutableLiveData<>();
    private final MutableLiveData<List<TrackPoint>> trackPoints = new MutableLiveData<>();

    public LandMapperViewModel(TrackRepository trackRepository, LocationRepository locationRepository) {
        this.trackRepository = trackRepository;
        this.locationRepository = locationRepository;

        // Initialize LiveData with repository data
        isRecording.setValue(trackRepository.isRecording());
        currentLocation.setValue(locationRepository.getCurrentLocation());
        trackPoints.setValue(trackRepository.getTrackPoints());

        // Observe changes in repositories if they provide observable data
        // This is a placeholder for actual implementation depending on repository design
    }

    public LiveData<Boolean> getIsRecording() {
        return isRecording;
    }

    public LiveData<Location> getCurrentLocation() {
        return currentLocation;
    }

    public LiveData<List<TrackPoint>> getTrackPoints() {
        return trackPoints;
    }

    // Add methods to start/stop recording and update location if needed
    public void startRecording() {
        trackRepository.startRecording();
        isRecording.setValue(true);
    }

    public void stopRecording() {
        trackRepository.stopRecording();
        isRecording.setValue(false);
    }

    public void updateCurrentLocation(Location location) {
        locationRepository.updateLocation(location);
        currentLocation.setValue(location);
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        trackRepository.addTrackPoint(trackPoint);
        List<TrackPoint> updatedPoints = trackRepository.getTrackPoints();
        trackPoints.setValue(updatedPoints);
    }
}
