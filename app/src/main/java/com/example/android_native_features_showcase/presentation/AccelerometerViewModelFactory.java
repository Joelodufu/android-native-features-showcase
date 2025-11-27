package com.example.android_native_features_showcase.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.domain.usecase.GetAccelerometerData;

public class AccelerometerViewModelFactory implements ViewModelProvider.Factory {

    private final GetAccelerometerData getAccelerometerData;

    public AccelerometerViewModelFactory(GetAccelerometerData getAccelerometerData) {
        this.getAccelerometerData = getAccelerometerData;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AccelerometerViewModel.class)) {
            return (T) new AccelerometerViewModel(getAccelerometerData);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
