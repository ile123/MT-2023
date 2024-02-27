package com.example.asyncexecutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    Button button, buttonSinkrono, buttonAsinkrono;
    ImageView imageView;
    TextView textViewSinkrono, textViewAsinkrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        buttonSinkrono = findViewById(R.id.buttonSinkrono);
        buttonAsinkrono = findViewById(R.id.buttonAsinkrono);
        textViewSinkrono = findViewById(R.id.textViewSinkrono);
        textViewAsinkrono = findViewById(R.id.textViewAsinkrono);


        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((Button) view).setText("Pusti!!!");
                    imageView.setImageResource(android.R.drawable.star_big_on);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((Button) view).setText("Pritisni!!!");
                    imageView.setImageResource(android.R.drawable.star_big_off);
                }
                return false;
            }
        });

        buttonSinkrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textViewSinkrono.setText("42");
            }
        });

        buttonAsinkrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textViewAsinkrono.setText("43");
                            }
                        });
                    }
                });
            }
        });
    }
}