package com.example.android_native_features_showcase;

import android.app.Application;

public class LandMapperApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the AppDatabase singleton
        AppDatabase.getInstance(this);
    }
}
