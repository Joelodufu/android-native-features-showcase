package com.example.android_native_features_showcase.workers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.Data;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.android_native_features_showcase.data.repository.RecordingRepository;

import java.util.Map;
import android.app.Application;

public class UploadWorker extends Worker {

    public static final String KEY_FILE_PATH = "file_path";
    private RecordingRepository repository;

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        repository = new RecordingRepository((Application) context.getApplicationContext());
    }

    @NonNull
    @Override
    public Result doWork() {
        String filePath = getInputData().getString(KEY_FILE_PATH);
        if (filePath == null || filePath.isEmpty()) {
            return Result.failure();
        }

        final Result[] workResult = new Result[1];
        final Object lock = new Object();

        MediaManager.get().upload(filePath)
                .option("upload_preset", "recorded")
                .option("resource_type", "auto")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                        // Upload started
                    }

                    @Override
                    public void onProgress(String requestId, long bytesUploaded, long totalBytes) {
                        // Upload progress
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String url = (String) resultData.get("secure_url");
                        repository.markAsUploadedByFilePath(filePath, url);
                        
                        synchronized (lock) {
                            workResult[0] = Result.success();
                            lock.notify();
                        }
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        synchronized (lock) {
                            workResult[0] = Result.failure();
                            lock.notify();
                        }
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        synchronized (lock) {
                            workResult[0] = Result.retry();
                            lock.notify();
                        }
                    }
                })
                .dispatch();

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                return Result.failure();
            }
        }

        return workResult[0] != null ? workResult[0] : Result.failure();
    }
}
