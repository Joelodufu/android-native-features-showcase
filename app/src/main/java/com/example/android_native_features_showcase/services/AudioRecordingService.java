package com.example.android_native_features_showcase.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.android_native_features_showcase.data.database.RecordingEntity;
import com.example.android_native_features_showcase.data.repository.RecordingRepository;
import com.example.android_native_features_showcase.workers.UploadWorker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AudioRecordingService extends Service {

    private MediaRecorder recorder;
    private String outputFilePath;
    private long startTime;

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

        // Create a unique file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = "REC_" + timeStamp + ".3gp";
        outputFilePath = getExternalCacheDir().getAbsolutePath() + "/" + fileName;
        
        recorder.setOutputFile(outputFilePath);

        try {
            recorder.prepare();
            recorder.start();
            startTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            try {
                recorder.stop();
            } catch (RuntimeException stopException) {
                stopException.printStackTrace();
            }
            recorder.release();
            recorder = null;

            long duration = System.currentTimeMillis() - startTime;
            File file = new File(outputFilePath);
            String fileName = file.getName();

            // Save to Database
            RecordingRepository repository = new RecordingRepository(getApplication());
            RecordingEntity recording = new RecordingEntity(outputFilePath, fileName, duration, System.currentTimeMillis());
            repository.insert(recording);

            // Create Data object with output file path
            Data uploadData = new Data.Builder()
                    .putString(UploadWorker.KEY_FILE_PATH, outputFilePath)
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
