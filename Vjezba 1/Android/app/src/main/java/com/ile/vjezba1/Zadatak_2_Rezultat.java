package com.ile.vjezba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Zadatak_2_Rezultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadatak2_rezultat);

        TextView result = findViewById(R.id.result_zad_2);
        Intent intent = getIntent();
        result.setText(intent.getStringExtra("result"));
    }
}