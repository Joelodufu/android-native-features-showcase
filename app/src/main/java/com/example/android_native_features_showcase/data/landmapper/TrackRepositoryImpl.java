package com.example.android_native_features_showcase.data.landmapper;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.android_native_features_showcase.data.landmapper.db.TrackDao;
import com.example.android_native_features_showcase.data.landmapper.db.TrackDatabase;
import com.example.android_native_features_showcase.data.landmapper.model.Track;
import com.example.android_native_features_showcase.data.landmapper.model.TrackPoint;
import com.example.android_native_features_showcase.domain.LocationRepository;
import com.example.android_native_features_showcase.domain.TrackRepository;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrackRepositoryImpl implements TrackRepository {

    private final TrackDao trackDao;
    private final LocationRepository locationRepository;
    private final ExecutorService executorService;

    public TrackRepositoryImpl(Context context, LocationRepository locationRepository) {
        TrackDatabase db = TrackDatabase.getInstance(context);
        this.trackDao = db.trackDao();
        this.locationRepository = locationRepository;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void startTracking() {
        locationRepository.startTracking();
    }

    @Override
    public void stopTracking() {
        locationRepository.stopTracking();
    }

    @Override
    public void saveTrack(Track track) {
        executorService.execute(() -> trackDao.insertTrack(track));
    }

    @Override
    public LiveData<List<Track>> loadTracks() {
        return trackDao.getAllTracks();
    }

    @Override
    public void saveTrackPoints(List<TrackPoint> points) {
        executorService.execute(() -> trackDao.insertTrackPoints(points));
    }

    @Override
    public LiveData<List<TrackPoint>> loadTrackPoints(long trackId) {
        return trackDao.getTrackPoints(trackId);
    }

    @Override
    public void deleteTrack(long trackId) {
        executorService.execute(() -> trackDao.deleteTrackById(trackId));
    }
}
