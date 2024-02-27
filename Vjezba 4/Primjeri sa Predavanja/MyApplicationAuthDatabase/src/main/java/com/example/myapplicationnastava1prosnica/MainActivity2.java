package com.example.myapplicationnastava1prosnica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity2 extends AppCompatActivity {

    TextView username;
    Button logout, save;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    EditText name, lecturer, semester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.textViewUseremail);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("information"));
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        name = findViewById(R.id.editTextCoarseName);
        lecturer = findViewById(R.id.editTextLecturer);
        semester = findViewById(R.id.editTextSemester);

        save = findViewById(R.id.buttonSaveData);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Predmet predmet = new Predmet(name.getText().toString(), lecturer.getText().toString(),Long.parseLong(semester.getText().toString()));
                saveUsertoDatastore(predmet);
            }
        });

        logout = findViewById(R.id.buttonLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();

            }
        });


    }

    void saveUsertoDatastore(Predmet predmet){
        db.collection("predmeti")
                .add(predmet)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("subject save status", "success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("subject save status", "failed");
                    }
                });

    }
}