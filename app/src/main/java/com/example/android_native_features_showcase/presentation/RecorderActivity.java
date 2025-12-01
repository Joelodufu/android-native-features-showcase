package com.example.android_native_features_showcase.presentation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.R;
import com.example.android_native_features_showcase.viewmodel.RecorderViewModel;

public class RecorderActivity extends AppCompatActivity {

    private RecorderViewModel recorderViewModel;
    private Button recordButton;
    private boolean isRecording = false;

    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        recorderViewModel = new ViewModelProvider(this).get(RecorderViewModel.class);

        recordButton = findViewById(R.id.record_button);

        // Setup permission request launcher
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        startOrStopRecording();
                    } else {
                        Toast.makeText(this, "Permission denied. Cannot record audio.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        recordButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED) {
                startOrStopRecording();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            }
        });

        // Observe recording state from ViewModel
        recorderViewModel.getIsRecording().observe(this, isRecording -> {
            this.isRecording = isRecording;
            updateUI();
        });

        // Observe any error messages
        recorderViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null && !errorMessage.isEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startOrStopRecording() {
        if (isRecording) {
            recorderViewModel.stopRecording();
        } else {
            recorderViewModel.startRecording();
        }
    }

    private void updateUI() {
        if (isRecording) {
            recordButton.setText(R.string.stop_recording);
        } else {
            recordButton.setText(R.string.start_recording);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startOrStopRecording();
            } else {
                Toast.makeText(this, "Permission denied. Cannot record audio.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
