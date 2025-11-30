package com.example.android_native_features_showcase.presentation.landmapper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_native_features_showcase.domain.TrackExporter;
import com.example.android_native_features_showcase.domain.TrackPoint;
import java.util.List;

public class LandMapperViewModel extends ViewModel {

    private final TrackExporter trackExporter;

    private final MutableLiveData<String> exportedTrackGeoJson = new MutableLiveData<>();
    private final MutableLiveData<List<TrackPoint>> trackPoints = new MutableLiveData<>();

    public LandMapperViewModel(TrackExporter trackExporter) {
        this.trackExporter = trackExporter;
    }

    /**
     * Exports the current track to a GeoJSON string and exposes it via LiveData.
     */
    public void exportCurrentTrack() {
        List<TrackPoint> currentTrackPoints = trackPoints.getValue();
        if (currentTrackPoints != null && !currentTrackPoints.isEmpty()) {
            String geoJson = trackExporter.exportToGeoJson(currentTrackPoints);
            exportedTrackGeoJson.setValue(geoJson);
        }
    }

    /**
     * Returns LiveData for the exported GeoJSON string of the current track.
     */
    public LiveData<String> getExportedTrackGeoJson() {
        return exportedTrackGeoJson;
    }

    /**
     * Sets the current list of track points and exposes it via LiveData.
     */
    public void setTrackPoints(List<TrackPoint> points) {
        trackPoints.setValue(points);
    }

    /**
     * Returns LiveData for the list of track points.
     */
    public LiveData<List<TrackPoint>> getTrackPoints() {
        return trackPoints;
    }
}
