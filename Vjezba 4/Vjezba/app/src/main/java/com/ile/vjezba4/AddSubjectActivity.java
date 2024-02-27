package com.ile.vjezba4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddSubjectActivity extends AppCompatActivity {

    EditText name;
    EditText professor;
    EditText year;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        name = findViewById(R.id.addSubjectName);
        professor = findViewById(R.id.addSubjectProfessor);
        year = findViewById(R.id.addSubjectYear);
        submit = findViewById(R.id.addSubjectSubmit);

        submit.setOnClickListener(v -> {
            Map<String, Object> data = new HashMap<>();
            data.put("name", name.getText().toString());
            data.put("professor", professor.getText().toString());
            data.put("year", Long.parseLong(year.getText().toString()));
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("vjezba4").add(data).addOnSuccessListener(documentReference -> finish());
        });

    }
}