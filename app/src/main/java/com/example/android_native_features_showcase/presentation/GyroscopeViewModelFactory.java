package com.example.android_native_features_showcase.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.domain.usecase.GetGyroscopeData;

public class GyroscopeViewModelFactory implements ViewModelProvider.Factory {

    private final GetGyroscopeData getGyroscopeData;

    public GyroscopeViewModelFactory(GetGyroscopeData getGyroscopeData) {
        this.getGyroscopeData = getGyroscopeData;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GyroscopeViewModel.class)) {
            return (T) new GyroscopeViewModel(getGyroscopeData);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
