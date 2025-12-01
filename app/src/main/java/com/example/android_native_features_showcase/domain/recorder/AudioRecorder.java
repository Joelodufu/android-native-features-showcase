package com.example.android_native_features_showcase.domain.recorder;

import java.io.File;

/**
 * Interface defining the contract for recording audio.
 */
public interface AudioRecorder {

    /**
     * Starts recording audio and saves it to the specified output file.
     *
     * @param outputFile The file where the recorded audio will be saved.
     */
    void start(File outputFile);

    /**
     * Stops the audio recording.
     */
    void stop();
}
