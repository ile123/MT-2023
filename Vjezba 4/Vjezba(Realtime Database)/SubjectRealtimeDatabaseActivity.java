package com.ile.vjezba4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ile.vjezba4.adapter.SubjectAdapter;
import com.ile.vjezba4.adapter.SubjectAdapterRealtimeDatabase;
import com.ile.vjezba4.entities.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectRealtimeDatabaseActivity extends AppCompatActivity {

    FirebaseAuth auth;
    SubjectAdapterRealtimeDatabase adapter;
    RecyclerView recyclerView;
    Button logoutButton, addNewSubjectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_realtime_database);

        recyclerView = findViewById(R.id.subjectsRealtimeDatabaseRecyclerView);
        logoutButton = findViewById(R.id.logoutRealtimeDatabaseButton);
        addNewSubjectButton = findViewById(R.id.addSubjectRealtimeDatabaseButton);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        auth = FirebaseAuth.getInstance();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("vjezba4");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Subject> subjectList = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    subjectList.add(dataSnapshot.getValue(Subject.class));
                }
                adapter = new SubjectAdapterRealtimeDatabase(subjectList, SubjectRealtimeDatabaseActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Realtime Database ERROR:", error.getMessage());
            }
        });

        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            goToLogin();
            finish();
        });

        addNewSubjectButton.setOnClickListener(v -> goToAddNewSubject());
    }

    public void goToEditSubject(String subjectId) {
        Intent intent = new Intent(SubjectRealtimeDatabaseActivity.this, EditSubjectActivity.class);
        intent.putExtra("subjectId", subjectId);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(SubjectRealtimeDatabaseActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToAddNewSubject() {
        Intent intent = new Intent(SubjectRealtimeDatabaseActivity.this, AddSubjectActivity.class);
        startActivity(intent);
    }
}