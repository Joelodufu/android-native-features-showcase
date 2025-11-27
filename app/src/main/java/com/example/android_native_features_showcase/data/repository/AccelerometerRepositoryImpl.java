package com.example.android_native_features_showcase.data.repository;

import com.example.android_native_features_showcase.data.sensor.AccelerometerSensor;
import com.example.android_native_features_showcase.domain.repository.AccelerometerRepository;
import com.example.android_native_features_showcase.domain.usecase.GetAccelerometerData;

public class AccelerometerRepositoryImpl implements AccelerometerRepository {

    private final AccelerometerSensor accelerometerSensor;

    public AccelerometerRepositoryImpl(AccelerometerSensor accelerometerSensor) {
        this.accelerometerSensor = accelerometerSensor;
    }

    @Override
    public void setListener(GetAccelerometerData.Listener listener) {
        accelerometerSensor.setListener(new AccelerometerSensor.Listener() {
            @Override
            public void onDataChanged(float x, float y, float z) {
                listener.onDataChanged(x, y, z);
            }
        });
    }
}
