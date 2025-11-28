package com.example.android_native_features_showcase.presentation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.R;
import com.example.android_native_features_showcase.data.repository.GyroscopeRepositoryImpl;
import com.example.android_native_features_showcase.data.sensor.AndroidGyroscopeSensor;
import com.example.android_native_features_showcase.domain.usecase.GetGyroscopeData;

public class MainActivity extends AppCompatActivity {

    private GyroscopeViewModel viewModel;
    private TextView gyroscopeDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gyroscopeDataTextView = findViewById(R.id.gyroscope_data);

        AndroidGyroscopeSensor androidGyroscopeSensor = new AndroidGyroscopeSensor(this);
        GyroscopeRepositoryImpl gyroscopeRepository = new GyroscopeRepositoryImpl(androidGyroscopeSensor);
        GetGyroscopeData getGyroscopeData = new GetGyroscopeData(gyroscopeRepository);
        GyroscopeViewModelFactory factory = new GyroscopeViewModelFactory(getGyroscopeData);
        viewModel = new ViewModelProvider(this, factory).get(GyroscopeViewModel.class);

        viewModel.getGyroscopeData().observe(this, data -> {
            gyroscopeDataTextView.setText(data);
        });
    }
}
