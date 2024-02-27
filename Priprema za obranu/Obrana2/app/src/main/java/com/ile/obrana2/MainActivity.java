package com.ile.obrana2;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button internet, coarse, contacts, fine, goToLocation;

    private int REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internet = findViewById(R.id.internetButton);
        coarse = findViewById(R.id.coarseButton);
        fine = findViewById(R.id.fineButton);
        contacts = findViewById(R.id.contactsButton);
        goToLocation = findViewById(R.id.goToLocationButton);

        ActivityCompat.requestPermissions(this, new String[]
                { Manifest.permission.INTERNET, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS},
                REQUEST_CODE);

        internet.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE);
            } else {
                Toast.makeText(MainActivity.this, "Internet access granted!", Toast.LENGTH_SHORT).show();
            }
        });

        coarse.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            } else {
                Toast.makeText(MainActivity.this, "Coarse location granted!", Toast.LENGTH_SHORT).show();
            }

        });

        fine.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            } else {
                Toast.makeText(MainActivity.this, "Fine location granted!", Toast.LENGTH_SHORT).show();
            }
        });

        contacts.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
            } else {
                Toast.makeText(MainActivity.this, "Read contacts granted!", Toast.LENGTH_SHORT).show();
            }
        });

        goToLocation.setOnClickListener(v -> startLocationActivity());
    }

    private void startLocationActivity() {
        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(intent);
    }
}