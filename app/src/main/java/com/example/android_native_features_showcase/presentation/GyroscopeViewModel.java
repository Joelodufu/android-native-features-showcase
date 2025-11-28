package com.example.android_native_features_showcase.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_native_features_showcase.domain.usecase.GetGyroscopeData;

public class GyroscopeViewModel extends ViewModel implements GetGyroscopeData.Listener {

    private final GetGyroscopeData getGyroscopeData;
    private final MutableLiveData<String> gyroscopeData = new MutableLiveData<>();

    public GyroscopeViewModel(GetGyroscopeData getGyroscopeData) {
        this.getGyroscopeData = getGyroscopeData;
        this.getGyroscopeData.execute(this);
    }

    public LiveData<String> getGyroscopeData() {
        return gyroscopeData;
    }

    @Override
    public void onDataChanged(float x, float y, float z) {
        String data = "X: " + x + "\nY: " + y + "\nZ: " + z;
        gyroscopeData.postValue(data);
    }
}
