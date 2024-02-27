package com.ile.obrana1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    EditText height;
    EditText weight;
    TextView result;
    TextView category;
    ImageView image;
    Button submit;
    Button changePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = findViewById(R.id.bmiHeight);
        weight = findViewById(R.id.bmiWeight);
        result = findViewById(R.id.bmiResult);
        category = findViewById(R.id.bmiCategory);
        image = findViewById(R.id.bmiImage);
        submit = findViewById(R.id.bmiSubmit);
        changePicture = findViewById(R.id.bmiImageButton);

        submit.setOnClickListener(v -> calculateBMI(height.getText().toString(), weight.getText().toString()));

        changePicture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        image.setImageResource(android.R.drawable.ic_dialog_alert);
                        return true;
                    case MotionEvent.ACTION_UP:
                        image.setImageResource(android.R.drawable.ic_dialog_info);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void calculateBMI(String height, String weight) {
        executorService.execute(() -> {
            float floatHeight = Float.parseFloat(height);
            float floatWeight = Float.parseFloat(weight);

            float bmi = (floatWeight / (floatHeight * floatHeight));

            try {
                Thread.sleep(5000);
                handler.post(() -> {
                    if(bmi < 19.0) {
                        result.setText(Float.toString(bmi));
                        category.setText("Pothranjeno");
                    } else if(bmi >= 19 && bmi <24) {
                        result.setText(Float.toString(bmi));
                        category.setText("Idealna tezina");
                    } else if(bmi >= 24 && bmi <29) {
                        result.setText(Float.toString(bmi));
                        category.setText("Prekomjerna tjelesna tezina");
                    } else if(bmi >= 29 && bmi <39) {
                        result.setText(Float.toString(bmi));
                        category.setText("Pretilost");
                    } else if(bmi >= 39) {
                        result.setText(Float.toString(bmi));
                        category.setText("Jaka pretilost");
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}