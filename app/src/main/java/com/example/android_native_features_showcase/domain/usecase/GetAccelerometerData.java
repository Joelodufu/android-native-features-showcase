package com.example.android_native_features_showcase.domain.usecase;

import com.example.android_native_features_showcase.domain.repository.AccelerometerRepository;

public class GetAccelerometerData {

    private final AccelerometerRepository repository;

    public GetAccelerometerData(AccelerometerRepository repository) {
        this.repository = repository;
    }

    public void execute(Listener listener) {
        repository.setListener(listener);
    }

    public interface Listener {
        void onDataChanged(float x, float y, float z);
    }
}
