package com.example.asyncroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    EditText name, occupation;
    FloatingActionButton button;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_name").build();
        name = findViewById(R.id.name);
        occupation = findViewById(R.id.occupation);
        button = findViewById(R.id.floatingActionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String personName = name.getText().toString();
                String personOccupation = occupation.getText().toString();

                if (TextUtils.isEmpty(personName) || TextUtils.isEmpty(personOccupation)) {

                    Toast.makeText(MainActivity2.this, "Input name and occupation", Toast.LENGTH_SHORT).show();
                }
                else {

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {

                            User user = new User(personName, personOccupation);
                            //user.name = personName;
                            //user.occupation = personOccupation;
                            db.userDao().insertAll(user);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity2.this.finish();
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}


