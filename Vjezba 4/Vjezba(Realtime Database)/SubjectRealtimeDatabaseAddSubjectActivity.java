package com.ile.vjezba4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ile.vjezba4.entities.Subject;

import java.util.Objects;

public class SubjectRealtimeDatabaseAddSubjectActivity extends AppCompatActivity {

    EditText name;
    EditText professor;
    EditText year;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_realtime_database_add_subject);

        name = findViewById(R.id.addSubjectNameRealtimeDatabse);
        professor = findViewById(R.id.addSubjectProfessorRealtimeDatabase);
        year = findViewById(R.id.addSubjectYearRealtimeDatabase);
        submit = findViewById(R.id.addSubjectSubmitRealtimeDatabase);

        submit.setOnClickListener(v -> {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("vjezba4");
            String newId = dbRef.push().getKey();
            Subject newSubject = new Subject(newId, name.getText().toString(), professor.getText().toString(), Long.parseLong(year.getText().toString()));
            dbRef.child(newId).setValue(newSubject);
        });
    }
}