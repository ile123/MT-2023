package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyLocalBroadcastReceiver extends BroadcastReceiver {

    TextView textView;

    public MyLocalBroadcastReceiver(TextView textView) {
        this.textView = textView;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("TEXT");
        textView.setText(data);
    }
}
