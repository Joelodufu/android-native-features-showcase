package com.example.android_native_features_showcase.presentation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.R;
import com.example.android_native_features_showcase.data.repository.AccelerometerRepositoryImpl;
import com.example.android_native_features_showcase.data.sensor.AndroidAccelerometerSensor;
import com.example.android_native_features_showcase.domain.usecase.GetAccelerometerData;

public class MainActivity extends AppCompatActivity {

    private AccelerometerViewModel viewModel;
    private TextView accelerometerDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerDataTextView = findViewById(R.id.accelerometer_data);

        AndroidAccelerometerSensor androidAccelerometerSensor = new AndroidAccelerometerSensor(this);
        AccelerometerRepositoryImpl accelerometerRepository = new AccelerometerRepositoryImpl(androidAccelerometerSensor);
        GetAccelerometerData getAccelerometerData = new GetAccelerometerData(accelerometerRepository);
        AccelerometerViewModelFactory factory = new AccelerometerViewModelFactory(getAccelerometerData);
        viewModel = new ViewModelProvider(this, factory).get(AccelerometerViewModel.class);

        viewModel.getAccelerometerData().observe(this, data -> {
            accelerometerDataTextView.setText(data);
        });
    }
}
