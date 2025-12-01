package com.example.android_native_features_showcase.data.recorder;

import android.media.MediaRecorder;
import java.io.IOException;

public class AudioRecorderImpl implements AudioRecorder {
    private MediaRecorder mediaRecorder;
    private String outputFilePath;

    public AudioRecorderImpl(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.mediaRecorder = new MediaRecorder();
    }

    @Override
    public void startRecording() throws IOException {
        try {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(outputFilePath);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            throw e;
        } catch (IllegalStateException | RuntimeException e) {
            throw new IOException("Failed to start recording", e);
        }
    }

    @Override
    public void stopRecording() {
        try {
            mediaRecorder.stop();
        } catch (RuntimeException e) {
            // Handle stop failure (e.g., if stop is called before start)
        } finally {
            mediaRecorder.reset();
            mediaRecorder.release();
        }
    }
}
