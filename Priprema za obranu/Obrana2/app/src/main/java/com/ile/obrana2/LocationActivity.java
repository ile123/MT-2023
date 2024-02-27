package com.ile.obrana2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class LocationActivity extends AppCompatActivity {

    private static final int locationCode = 1000;
    private double latitude = 0.0, longitude = 0.0;
    private Geocoder geocoder;
    private final LocationRequest locationRequest = new LocationRequest()
            .setInterval(5000)
            .setFastestInterval(2000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    private FusedLocationProviderClient fusedLocationClient;
    private TextView longitudeView, latitudeView, streetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        longitudeView = findViewById(R.id.longitudeView);
        latitudeView = findViewById(R.id.latitudeView);
        streetView = findViewById(R.id.cityCodeView);

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission not granted!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            startLocationUpdates();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == locationCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        }
    }
    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                locationResult.getLastLocation();
                Location location = locationResult.getLastLocation();
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                updateLocationText();
            }
        }, null);
    }
    private void updateLocationText() {
        longitudeView.setText(String.format(Double.toString(longitude)));
        latitudeView.setText(String.format(Double.toString(latitude)));
        try {
            Address address = Objects.requireNonNull(geocoder.getFromLocation(latitude, longitude, 1)).get(0);
            streetView.setText(address.getPostalCode());
        } catch (IOException e) {
            streetView.setText("Address unavailable");
        }
    }

}