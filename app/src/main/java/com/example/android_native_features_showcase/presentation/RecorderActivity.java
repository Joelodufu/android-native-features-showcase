package com.example.android_native_features_showcase.presentation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_native_features_showcase.R;
import com.example.android_native_features_showcase.data.RecordingAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecorderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecordingAdapter recordingAdapter;
    private List<String> recordings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        // Initialize recordings list
        recordings = new ArrayList<>();

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewRecordings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter
        recordingAdapter = new RecordingAdapter(recordings);
        recyclerView.setAdapter(recordingAdapter);

        // Load recordings from local storage
        loadRecordings();

        // Existing functionality here (if any) should remain intact
    }

    // Method to load recordings from local storage
    private void loadRecordings() {
        File recordingsDir = getFilesDir();
        File[] files = recordingsDir.listFiles();
        recordings.clear();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    recordings.add(file.getName());
                }
            }
        }
        recordingAdapter.notifyDataSetChanged();
    }

    // Call this method when new recordings are created to update the adapter
    public void updateRecordings() {
        loadRecordings();
    }

}
