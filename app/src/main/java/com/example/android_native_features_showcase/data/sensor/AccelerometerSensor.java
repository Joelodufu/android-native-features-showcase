package com.example.android_native_features_showcase.data.sensor;

public interface AccelerometerSensor {
    void startListening();
    void stopListening();
    void setListener(Listener listener);

    interface Listener {
        void onDataChanged(float x, float y, float z);
    }
}
