package com.example.android_native_features_showcase.data.repository;

import com.example.android_native_features_showcase.data.sensor.GyroscopeSensor;
import com.example.android_native_features_showcase.domain.repository.GyroscopeRepository;
import com.example.android_native_features_showcase.domain.usecase.GetGyroscopeData;

public class GyroscopeRepositoryImpl implements GyroscopeRepository {

    private final GyroscopeSensor gyroscopeSensor;

    public GyroscopeRepositoryImpl(GyroscopeSensor gyroscopeSensor) {
        this.gyroscopeSensor = gyroscopeSensor;
    }

    @Override
    public void setListener(GetGyroscopeData.Listener listener) {
        gyroscopeSensor.setListener(new GyroscopeSensor.Listener() {
            @Override
            public void onDataChanged(float x, float y, float z) {
                listener.onDataChanged(x, y, z);
            }
        });
    }
}
