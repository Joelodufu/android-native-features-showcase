package com.example.android_native_features_showcase.data.landmapper;

import android.location.Location;
import java.util.List;

/**
 * Repository interface for managing track points and land tracks.
 */
public interface TrackRepository {

    /**
     * Starts the tracking process.
     */
    void startTracking();

    /**
     * Stops the tracking process.
     */
    void stopTracking();

    /**
     * Gets the current location.
     *
     * @return the current Location or null if not available
     */
    Location getCurrentLocation();

    /**
     * Saves a track with the given name and list of locations.
     *
     * @param trackName the name of the track
     * @param locations the list of locations representing the track points
     */
    void saveTrack(String trackName, List<Location> locations);

    /**
     * Loads a track by its name.
     *
     * @param trackName the name of the track to load
     * @return the list of locations representing the track points or null if not found
     */
    List<Location> loadTrack(String trackName);

    /**
     * Gets a list of all saved track names.
     *
     * @return list of track names
     */
    List<String> getAllTrackNames();
}
