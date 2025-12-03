package com.example.android_native_features_showcase.presentation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_native_features_showcase.R;
import com.example.android_native_features_showcase.services.AudioRecordingService;

public class RecorderActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private Button recordButton;
    private RecyclerView recyclerView;
    private RecordingAdapter adapter;
    private RecorderViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        // Request permissions
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        // Initialize UI
        recordButton = findViewById(R.id.btnRecord); // Ensure this ID matches your layout
        recyclerView = findViewById(R.id.recyclerViewRecordings); // Ensure this ID matches your layout

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordingAdapter();
        recyclerView.setAdapter(adapter);

        // Setup ViewModel
        viewModel = new ViewModelProvider(this).get(RecorderViewModel.class);
        viewModel.getRecordings().observe(this, recordings -> {
            adapter.setRecordings(recordings);
        });

        // Setup Button Listener
        recordButton.setOnClickListener(v -> {
            if (viewModel.isRecording()) {
                viewModel.stopRecording();
                recordButton.setText("Start Recording");
            } else {
                viewModel.startRecording();
                recordButton.setText("Stop Recording");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionToRecordAccepted) {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
