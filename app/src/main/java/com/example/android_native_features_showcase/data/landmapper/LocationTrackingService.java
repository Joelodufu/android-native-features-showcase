package com.example.android_native_features_showcase.data.landmapper;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.android_native_features_showcase.R;

public class LocationTrackingService extends Service {

    private static final String CHANNEL_ID = "location_tracking_channel";
    private long trackId;
    private TrackRepository trackRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        trackRepository = new TrackRepository(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 1. Extract a "track_id" from the Intent extras. If missing, generate one
        if (intent != null && intent.hasExtra("track_id")) {
            trackId = intent.getLongExtra("track_id", System.currentTimeMillis());
        } else {
            trackId = System.currentTimeMillis();
        }

        // 2. Create a new LandTrack entity with this ID and save it
        LandTrack track = new LandTrack(trackId);
        trackRepository.insertTrack(track);

        // 3. Store this trackId in a member variable (already done above)

        // 4. Start the foreground notification
        startForeground(1, createNotification());

        // 5. Request location updates
        requestLocationUpdates();

        return START_STICKY;
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Tracking")
                .setContentText("Tracking your location")
                .setSmallIcon(R.drawable.ic_location)
                .setContentIntent(pendingIntent)
                .build();
    }

    private void requestLocationUpdates() {
        // Implementation to request location updates and set a callback
        // Assuming a LocationCallback is set up to receive location updates
        // For example:
        // fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    // Location callback example
    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                // 1. Use the stored trackId to create TrackPoint objects
                TrackPoint point = new TrackPoint(trackId, location.getLatitude(), location.getLongitude(), location.getTime());

                // 2. Save them via trackRepository.insertPoint(point)
                trackRepository.insertPoint(point);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

// Note: You may need to import or define LocationCallback and LocationResult classes depending on your location API usage.
// Also ensure TrackRepository, LandTrack, TrackPoint, and MainActivity are properly defined in your project.
