package com.example.android_native_features_showcase;

import android.app.Application;

public class ShowcaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CloudinaryHelper.init(this);
    }
}
