package com.ile.seminar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ile.seminar.adapters.ScoreAdapter;
import com.ile.seminar.models.PlayerScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerScoreActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ScoreAdapter adapter;
    RecyclerView recyclerView;
    int indexNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_score);

        recyclerView = findViewById(R.id.playerScoreRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CollectionReference collection = db.collection("users")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("scores");

        collection.addSnapshotListener((value, error) -> collection
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        List<PlayerScore> playerScoreList = new ArrayList<>();
                        for(DocumentSnapshot documentSnapshot : task.getResult()) {
                            PlayerScore playerScore = new PlayerScore(
                                    String.valueOf(indexNumber),
                                    String.valueOf(documentSnapshot.getLong("correctAnswers")),
                                    String.valueOf(documentSnapshot.getLong("score")));
                            playerScoreList.add(playerScore);
                            indexNumber++;
                        }
                        adapter = new ScoreAdapter(playerScoreList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(PlayerScoreActivity.this, "ERROR: Problems with fetching user scores!", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}