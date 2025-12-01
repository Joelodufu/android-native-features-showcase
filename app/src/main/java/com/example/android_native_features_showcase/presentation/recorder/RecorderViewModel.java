package com.example.android_native_features_showcase.presentation.recorder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecorderViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isRecording = new MutableLiveData<>(false);
    private final AudioRecorder audioRecorder = new AudioRecorder();

    public LiveData<Boolean> getIsRecording() {
        return isRecording;
    }

    public void startRecording() {
        audioRecorder.start();
        isRecording.setValue(true);
    }

    public void stopRecording() {
        audioRecorder.stop();
        isRecording.setValue(false);
    }

    public boolean isRecording() {
        Boolean recording = isRecording.getValue();
        return recording != null && recording;
    }
}

// Note: AudioRecorder class should be implemented or imported accordingly in the project.