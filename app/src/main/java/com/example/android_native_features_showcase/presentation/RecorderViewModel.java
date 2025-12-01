package com.example.android_native_features_showcase.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_native_features_showcase.domain.AudioRecorder;

public class RecorderViewModel extends ViewModel {

    private final AudioRecorder audioRecorder;

    private final MutableLiveData<Boolean> isRecording = new MutableLiveData<>(false);

    public RecorderViewModel(AudioRecorder audioRecorder) {
        this.audioRecorder = audioRecorder;
    }

    public LiveData<Boolean> getIsRecording() {
        return isRecording;
    }

    public void startRecording() {
        if (!isRecording.getValue()) {
            audioRecorder.startRecording();
            isRecording.setValue(true);
        }
    }

    public void stopRecording() {
        if (isRecording.getValue()) {
            audioRecorder.stopRecording();
            isRecording.setValue(false);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (isRecording.getValue()) {
            audioRecorder.stopRecording();
            isRecording.setValue(false);
        }
    }
}
