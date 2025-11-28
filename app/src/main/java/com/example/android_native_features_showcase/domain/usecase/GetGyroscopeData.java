package com.example.android_native_features_showcase.domain.usecase;

import com.example.android_native_features_showcase.domain.repository.GyroscopeRepository;

public class GetGyroscopeData {

    private final GyroscopeRepository repository;

    public GetGyroscopeData(GyroscopeRepository repository) {
        this.repository = repository;
    }

    public void execute(Listener listener) {
        repository.setListener(listener);
    }

    public interface Listener {
        void onDataChanged(float x, float y, float z);
    }
}
