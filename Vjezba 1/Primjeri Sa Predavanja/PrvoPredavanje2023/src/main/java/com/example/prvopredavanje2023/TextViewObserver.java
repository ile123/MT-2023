package com.example.prvopredavanje2023;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class TextViewObserver extends androidx.appcompat.widget.AppCompatTextView implements DefaultLifecycleObserver {

    public TextViewObserver(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        ((AppCompatActivity)context).getLifecycle().addObserver(this);
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        Log.d("Lifecycle", "Paused");
        this.setText("Paused");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onResume(owner);
        Log.d("Lifecycle", "Resumed");
        this.setText("resumed");
    }
}
