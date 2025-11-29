package com.example.android_native_features_showcase.presentation.landmapper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.example.android_native_features_showcase.databinding.ActivityLandMapperBinding;

public class LandMapperActivity extends AppCompatActivity {

    private ActivityLandMapperBinding binding;
    private LandMapperViewModel viewModel;

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    viewModel.onLocationPermissionGranted();
                } else {
                    viewModel.onLocationPermissionDenied();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandMapperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LandMapperViewModel.Factory factory = new LandMapperViewModel.Factory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(LandMapperViewModel.class);

        observeViewModel();

        checkLocationPermissionAndRequestIfNeeded();
    }

    private void observeViewModel() {
        viewModel.getState().observe(this, state -> {
            // Update UI based on state
            binding.textViewStatus.setText(state.getStatusMessage());
            // Add more UI updates as needed
        });
    }

    private void checkLocationPermissionAndRequestIfNeeded() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            viewModel.onLocationPermissionGranted();
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.onLocationPermissionGranted();
            } else {
                viewModel.onLocationPermissionDenied();
            }
        }
    }
}
