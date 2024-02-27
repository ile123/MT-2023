package com.example.prvopredavanje2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView textView;
    TextViewObserver textViewObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        try {
            textView = findViewById(R.id.textView2);
            textViewObserver = findViewById(R.id.text);
            Intent intent = getIntent();
            textView.setText(intent.getStringExtra("poruka"));
        }



        catch(Exception e){
            Log.d("error", e.toString());
        }
    }
}