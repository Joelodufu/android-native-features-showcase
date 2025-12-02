package com.example.android_native_features_showcase.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_native_features_showcase.data.Recording;

import java.util.ArrayList;
import java.util.List;

public class RecorderViewModel extends ViewModel {

    // Existing recording state management fields and methods

    private final MutableLiveData<List<Recording>> recordings = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Recording>> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordingsList) {
        recordings.setValue(recordingsList);
    }

    // Method to load recordings from local storage
    public void loadRecordingsFromLocalStorage() {
        // This is a placeholder for actual loading logic
        // For example, you might load from a database or file system
        List<Recording> loadedRecordings = new ArrayList<>();
        // TODO: Implement actual loading logic here

        // Update the LiveData with loaded recordings
        recordings.setValue(loadedRecordings);
    }

    // Method to update recordings list when a new recording is created
    public void addNewRecording(Recording newRecording) {
        List<Recording> currentRecordings = recordings.getValue();
        if (currentRecordings == null) {
            currentRecordings = new ArrayList<>();
        }
        currentRecordings.add(newRecording);
        recordings.setValue(currentRecordings);
    }

    // Keep all existing recording state management functionality intact

}
