package com.example.android_native_features_showcase.presentation.recorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_native_features_showcase.databinding.ActivityRecorderBinding;

public class RecorderActivity extends AppCompatActivity {

    private ActivityRecorderBinding binding;
    private RecorderViewModel viewModel;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    startOrStopRecording();
                } else {
                    Toast.makeText(this, "Permission denied. Cannot record audio.", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecorderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RecorderViewModel.class);

        binding.recordButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                startOrStopRecording();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            }
        });
    }

    private void startOrStopRecording() {
        if (viewModel.isRecording()) {
            viewModel.stopRecording();
            binding.recordButton.setText("Start Recording");
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.startRecording();
            binding.recordButton.setText("Stop Recording");
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
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
