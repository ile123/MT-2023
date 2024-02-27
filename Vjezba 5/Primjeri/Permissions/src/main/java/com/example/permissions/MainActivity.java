package com.example.permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int FINE_LOCATION_REQUEST_IDENTIFIER = 101;
    private static final int CAMERA_REQUEST_IDENTIFIER = 102;
    private static final int CONTACTS_REQUEST_IDENTIFIER = 103;


    Button location, camera, contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.buttonCameraPerm);
        location = findViewById(R.id.buttonLocationPerm);
        contacts = findViewById(R.id.buttonReacContactsPerm);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_IDENTIFIER);

                } else {
                    Toast.makeText(MainActivity.this, "prava na kameru su vec dana", Toast.LENGTH_SHORT).show();
                }
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CAMERA_REQUEST_IDENTIFIER);

                } else {
                    Toast.makeText(MainActivity.this, "prava na lokaciju su vec dana", Toast.LENGTH_SHORT).show();
                }
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CAMERA_REQUEST_IDENTIFIER);

                } else {
                    Toast.makeText(MainActivity.this, "prava na citanje kontakata su vec dana", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case CAMERA_REQUEST_IDENTIFIER:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Prava na kameru su upravo dana!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Prava na kameru su odbijena!", Toast.LENGTH_SHORT).show();
                }break;
            }


            case FINE_LOCATION_REQUEST_IDENTIFIER:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Prava na lokaciju su upravo dana!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Prava na lokaciju su odbijena!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case CONTACTS_REQUEST_IDENTIFIER:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Prava na citanje kontakata su upravo dana!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Prava na citanje kontakata su odbijena!", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }
}