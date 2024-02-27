package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView permitionTextView, longitude, latitude, addressView;
    private static final int FINE_LOCATION_REQUEST_IDENTIFIER = 101;



    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permitionTextView = findViewById(R.id.permitionStatus);
        addressView = findViewById(R.id.textViewAdress);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_IDENTIFIER);


        } else {
            permitionTextView.setText("prava na lokaciju su vec dana");
            setupLocationTracking();

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permitionTextView.setText("Prava na lokaciju su upravo dana!");
                    Toast.makeText(this, "Lokacija je upravo dozovljena", Toast.LENGTH_SHORT).show();
                    setupLocationTracking();
                }
                else{
                    Toast.makeText(this, "Lokacija nije dozovljena", Toast.LENGTH_SHORT).show();
               }

    }


    @SuppressLint("MissingPermission")
    public void setupLocationTracking() {
        @SuppressLint("ServiceCast") LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        geocoder = Geocoder.isPresent() ? new Geocoder(this):null;

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            longitude = findViewById(R.id.textViewLongitude);
            latitude = findViewById(R.id.textViewLatitude);
            longitude.setText(Double.toString(location.getLongitude()));
            latitude.setText(Double.toString(location.getLatitude()));
            Log.d("longitude", Double.toString(location.getLongitude()));
            Log.d("latitude", Double.toString(location.getLatitude()));

                if (geocoder != null) {

                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        if(addresses.size() > 0){
                            Address address = addresses.get(0);
                            addressView.setText(address.getThoroughfare() + " " + address.getFeatureName() + " " + address.getLocality());
                        }

                    } catch (IOException e) {
                        //throw new RuntimeException(e);
                        Log.d("greska", new RuntimeException(e).toString());
                    }
                }

            }
        };


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 100, locationListener);
    }
}