package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyOtherBroadcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_BATTERY_LOW:
                Toast.makeText(context, "POWER DISCONNECTED ;-) ", Toast.LENGTH_LONG).show();
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                Toast.makeText(context, "AIRPLANE MODE CHANGED", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
