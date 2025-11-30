package com.example.android_native_features_showcase.presentation.landmapper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_native_features_showcase.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class LandMapperActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private LandMapperViewModel viewModel;
    private Polyline currentPolyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_mapper);

        viewModel = new ViewModelProvider(this).get(LandMapperViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Button btnExport = findViewById(R.id.btn_export);
        btnExport.setOnClickListener(v -> viewModel.exportCurrentTrack());

        viewModel.getExportedTrack().observe(this, geoJson -> {
            if (geoJson != null && !geoJson.isEmpty()) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("application/json");
                shareIntent.putExtra(Intent.EXTRA_TEXT, geoJson);
                startActivity(Intent.createChooser(shareIntent, "Share GeoJSON"));
            }
        });

        viewModel.getTrackPoints().observe(this, trackPoints -> {
            if (map == null || trackPoints == null || trackPoints.isEmpty()) return;

            if (currentPolyline != null) {
                currentPolyline.remove();
            }

            PolylineOptions polylineOptions = new PolylineOptions();
            for (LatLng point : trackPoints) {
                polylineOptions.add(point);
            }

            currentPolyline = map.addPolyline(polylineOptions);

            LatLng lastPoint = trackPoints.get(trackPoints.size() - 1);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(lastPoint, 15f));
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (map != null) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    }
                }
            }
        }
    }
}
