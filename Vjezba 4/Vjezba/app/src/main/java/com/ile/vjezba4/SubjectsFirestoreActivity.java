package com.ile.vjezba4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ile.vjezba4.adapter.SubjectAdapter;
import com.ile.vjezba4.entities.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectsFirestoreActivity extends AppCompatActivity {

    FirebaseAuth auth;
    SubjectAdapter adapter;
    RecyclerView recyclerView;
    Button logoutButton, addNewSubjectButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collection = db.collection("vjezba4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_firestore);

        recyclerView = findViewById(R.id.subjectRecycleView);
        logoutButton = findViewById(R.id.logoutButton);
        addNewSubjectButton = findViewById(R.id.addNewSubjectButton);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        auth = FirebaseAuth.getInstance();

        collection.addSnapshotListener((value, error) -> collection
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        List<Subject> subjectList = new ArrayList<>();
                        for(DocumentSnapshot document : task.getResult()) {
                            Subject subject = new Subject(
                                    document.getId(),
                                    document.getString("name"),
                                    document.getString("professor"),
                                    document.getLong("year").intValue());
                            subjectList.add(subject);
                        }
                        adapter = new SubjectAdapter(subjectList, SubjectsFirestoreActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                }));

        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            goToLogin();
            finish();
        });

        addNewSubjectButton.setOnClickListener(v -> goToAddNewSubject());
    }

    public void goToEditSubject(String subjectId) {
        Intent intent = new Intent(SubjectsFirestoreActivity.this, EditSubjectActivity.class);
        intent.putExtra("subjectId", subjectId);
        startActivity(intent);
    }

    private void goToLogin() {
        Intent intent = new Intent(SubjectsFirestoreActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToAddNewSubject() {
        Intent intent = new Intent(SubjectsFirestoreActivity.this, AddSubjectActivity.class);
        startActivity(intent);
    }
}