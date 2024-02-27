package com.ile.vjezba4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SubjectRealtimeDatabaseEditSubjectActivity extends AppCompatActivity {

    EditText name;
    EditText professor;
    EditText year;
    Button submit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_realtime_database_edit_subject);

        name = findViewById(R.id.editSubjectNameRealtimeDatabase);
        professor = findViewById(R.id.editSubjectProfessorRealtimeDatabase);
        year = findViewById(R.id.editSubjectYearRealtimeDatabse);
        submit = findViewById(R.id.editSubjectSubmitRealtimeDatabse);
        delete = findViewById(R.id.editSubjectDeleteRealtimeDatabase);

        Intent intent = getIntent();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("vjezba4").child(Objects.requireNonNull(intent.getStringExtra("subjectId")));
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue(String.class));
                professor.setText(snapshot.child("professor").getValue(String.class));
                year.setText(String.valueOf(snapshot.child("year").getValue(Long.class)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Realtime Database ERROR:", error.getMessage());
            }
        });

        submit.setOnClickListener(v -> {
            Map<String, Object> data = new HashMap<>();
            data.put("name", name.getText().toString());
            data.put("professor", professor.getText().toString());
            data.put("year", Long.parseLong(year.getText().toString()));
            dbRef.updateChildren(data);
        });

        delete.setOnClickListener(v -> {
            dbRef.removeValue();
            finish();
        });
    }
}