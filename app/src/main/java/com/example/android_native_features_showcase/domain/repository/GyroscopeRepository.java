package com.example.android_native_features_showcase.domain.repository;

import com.example.android_native_features_showcase.domain.usecase.GetGyroscopeData;

public interface GyroscopeRepository {
    void setListener(GetGyroscopeData.Listener listener);
}
