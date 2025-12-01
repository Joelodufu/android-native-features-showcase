package com.example.android_native_features_showcase.presentation.landmapper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.android_native_features_showcase.R;

public class LandMapperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mapper);

        findViewById(R.id.startRecordingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a trackId as a long timestamp
                long trackId = System.currentTimeMillis();

                // Create an Intent for LocationTrackingService
                Intent intent = new Intent(LandMapperActivity.this, LocationTrackingService.class);

                // Put extra "track_id" = trackId
                intent.putExtra("track_id", trackId);

                // Start the service as a foreground service
                ContextCompat.startForegroundService(LandMapperActivity.this, intent);

                // (Optional) Update UI state to "Recording"
                // e.g., update a TextView or button state here
            }
        });

        findViewById(R.id.stopRecordingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent for LocationTrackingService
                Intent intent = new Intent(LandMapperActivity.this, LocationTrackingService.class);

                // Stop the service
                stopService(intent);

                // (Optional) Update UI state to "Idle"
                // e.g., update a TextView or button state here
            }
        });

        // Keep existing Map and Export logic unchanged
    }
}
