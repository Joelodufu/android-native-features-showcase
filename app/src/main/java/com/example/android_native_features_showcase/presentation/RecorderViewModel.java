package com.example.android_native_features_showcase.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_native_features_showcase.data.database.RecordingEntity;
import com.example.android_native_features_showcase.data.repository.RecordingRepository;

import java.util.List;

public class RecorderViewModel extends AndroidViewModel {

    private RecordingRepository repository;
    private final LiveData<List<RecordingEntity>> allRecordings;

    public RecorderViewModel(@NonNull Application application) {
        super(application);
        repository = new RecordingRepository(application);
        allRecordings = repository.getAllRecordings();
    }

    public LiveData<List<RecordingEntity>> getRecordings() {
        return allRecordings;
    }

    public void insert(RecordingEntity recording) {
        repository.insert(recording);
    }
    
    public void delete(RecordingEntity recording) {
        repository.delete(recording);
    }
}
