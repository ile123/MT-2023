package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    private BroadcastReceiver broadcastReceiver, broadcastReceiver2;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        broadcastReceiver = new MyLocalBroadcastReceiver(this.textView);
        broadcastReceiver2 = new MyOtherBroadcastReceiver();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent = new Intent("MOJ BROADCAST");
                localIntent.putExtra("TEXT", MainActivity.this.editText.getText().toString());
                MainActivity.this.localBroadcastManager.sendBroadcast(localIntent);

            }
        });

        localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter("MOJ BROADCAST"));



    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        this.registerReceiver(broadcastReceiver2, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(broadcastReceiver2);
    }
}