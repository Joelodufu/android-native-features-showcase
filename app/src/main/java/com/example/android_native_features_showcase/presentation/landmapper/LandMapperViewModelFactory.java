package com.example.android_native_features_showcase.presentation.landmapper;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.data.repository.TrackRepository;
import com.example.android_native_features_showcase.data.repository.LocationRepository;

public class LandMapperViewModelFactory implements ViewModelProvider.Factory {

    private final TrackRepository trackRepository;
    private final LocationRepository locationRepository;

    public LandMapperViewModelFactory(TrackRepository trackRepository, LocationRepository locationRepository) {
        this.trackRepository = trackRepository;
        this.locationRepository = locationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LandMapperViewModel.class)) {
            return (T) new LandMapperViewModel(trackRepository, locationRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
