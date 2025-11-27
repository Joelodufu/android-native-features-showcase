package com.example.android_native_features_showcase.domain.repository;

import com.example.android_native_features_showcase.domain.usecase.GetAccelerometerData;

public interface AccelerometerRepository {
    void setListener(GetAccelerometerData.Listener listener);
}
