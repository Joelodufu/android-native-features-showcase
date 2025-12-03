package com.example.android_native_features_showcase.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android_native_features_showcase.data.database.AppDatabase;
import com.example.android_native_features_showcase.data.database.RecordingDao;
import com.example.android_native_features_showcase.data.database.RecordingEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RecordingRepository {

    private RecordingDao recordingDao;
    private LiveData<List<RecordingEntity>> allRecordings;
    private ExecutorService executorService;

    public RecordingRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        recordingDao = db.recordingDao();
        allRecordings = recordingDao.getAllRecordings();
        executorService = AppDatabase.databaseWriteExecutor;
    }

    public LiveData<List<RecordingEntity>> getAllRecordings() {
        return allRecordings;
    }

    public void insert(RecordingEntity recording) {
        executorService.execute(() -> {
            recordingDao.insert(recording);
        });
    }
    
    public void update(RecordingEntity recording) {
        executorService.execute(() -> {
            recordingDao.update(recording);
        });
    }

    public void delete(RecordingEntity recording) {
        executorService.execute(() -> {
            recordingDao.delete(recording);
        });
    }
    
    public void markAsUploaded(int id, String url) {
        executorService.execute(() -> {
            recordingDao.markAsUploaded(id, url);
        });
    }

    public void markAsUploadedByFilePath(String filePath, String url) {
        executorService.execute(() -> {
            recordingDao.markAsUploadedByFilePath(filePath, url);
        });
    }
}