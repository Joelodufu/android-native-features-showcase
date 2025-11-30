package com.example.android_native_features_showcase.data.landmapper;

import com.example.android_native_features_showcase.data.landmapper.LandTrack;

/**
 * Interface defining the contract for exporting a LandTrack to a formatted string (e.g., GeoJSON).
 */
public interface TrackExporter {

    /**
     * Export the given LandTrack to a formatted string.
     *
     * @param track the LandTrack to export
     * @return the formatted string representation of the LandTrack
     */
    String export(LandTrack track);
}
