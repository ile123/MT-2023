package com.ile.vjezba4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditSubjectActivity extends AppCompatActivity {

    EditText name;
    EditText professor;
    EditText year;
    Button submit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        name = findViewById(R.id.addSubjectName);
        professor = findViewById(R.id.addSubjectProfessor);
        year = findViewById(R.id.addSubjectYear);
        submit = findViewById(R.id.addSubjectSubmit);
        delete = findViewById(R.id.deleteSubjectButton);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        DocumentReference reference = db.collection("vjezba4").document(intent.getStringExtra("subjectId"));
        reference.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()) {
                int subjectYear = documentSnapshot.getLong("year").intValue();
                name.setText(documentSnapshot.getString("name"));
                professor.setText(documentSnapshot.getString("professor"));
                year.setText(String.valueOf(subjectYear));
            }
        });

        submit.setOnClickListener(v -> {
            Map<String, Object> newValues = new HashMap<>();
            newValues.put("name", name.getText().toString());
            newValues.put("professor", professor.getText().toString());
            newValues.put("year", Integer.parseInt(year.getText().toString()));
            reference.update(newValues).addOnSuccessListener(unused -> finish());
        });

        delete.setOnClickListener(v -> reference.delete().addOnSuccessListener(unused -> finish()));
    }
}