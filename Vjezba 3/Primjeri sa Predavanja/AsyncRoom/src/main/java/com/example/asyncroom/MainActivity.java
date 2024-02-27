package com.example.asyncroom;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public static int ADD_ACTIVITY_RESULT_CODE = 1;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    AppDatabase db;
    TextView textView;
    FloatingActionButton buttonAdd;
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "database_name").build();


        buttonAdd = findViewById(R.id.floatingActionButton4);





        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPerson();
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<User> users = db.userDao().getAll();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        adapter = new CustomAdapter(users);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }

        });
    }

    public void addNewPerson(){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);

    }


}