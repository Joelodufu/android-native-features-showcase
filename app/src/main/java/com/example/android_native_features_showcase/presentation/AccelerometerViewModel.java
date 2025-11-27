package com.example.android_native_features_showcase.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_native_features_showcase.domain.usecase.GetAccelerometerData;

public class AccelerometerViewModel extends ViewModel implements GetAccelerometerData.Listener {

    private final GetAccelerometerData getAccelerometerData;
    private final MutableLiveData<String> accelerometerData = new MutableLiveData<>();

    public AccelerometerViewModel(GetAccelerometerData getAccelerometerData) {
        this.getAccelerometerData = getAccelerometerData;
        this.getAccelerometerData.execute(this);
    }

    public LiveData<String> getAccelerometerData() {
        return accelerometerData;
    }

    @Override
    public void onDataChanged(float x, float y, float z) {
        String data = "X: " + x + "\nY: " + y + "\nZ: " + z;
        accelerometerData.postValue(data);
    }
}
