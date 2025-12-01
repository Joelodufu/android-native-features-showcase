package com.example.android_native_features_showcase.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.android_native_features_showcase.workers.UploadWorker;

import java.io.IOException;

public class AudioRecordingService extends Service {

    private MediaRecorder recorder;
    private String outputFilePath;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        outputFilePath = getExternalCacheDir().getAbsolutePath() + "/audiorecordtest.3gp";
        recorder.setOutputFile(outputFilePath);

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            try {
                recorder.stop();
            } catch (RuntimeException stopException) {
                // Handle stop failure
                stopException.printStackTrace();
            }
            recorder.release();
            recorder = null;

            // Create Data object with output file path
            Data uploadData = new Data.Builder()
                .putString("file_path", outputFilePath)
                .build();

            // Create OneTimeWorkRequest for UploadWorker
            OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .setInputData(uploadData)
                .build();

            // Enqueue the work
            WorkManager.getInstance(this).enqueue(uploadWorkRequest);
        }
    }

    @Override
    public void onDestroy() {
        stopRecording();
        super.onDestroy();
    }
}
