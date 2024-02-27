package com.ile.seminar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ile.seminar.adapters.PlayerAdapter;
import com.ile.seminar.models.BestPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BestPlayersActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    int indexNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_players);

        recyclerView = findViewById(R.id.bestPlayersRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CollectionReference collection = db.collection("users");

        collection.addSnapshotListener((value, error) -> collection
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        List<BestPlayer> bestPlayerList = new ArrayList<>();
                        for(DocumentSnapshot documentSnapshot : task.getResult()) {
                            BestPlayer bestPlayer = new BestPlayer(
                                    String.valueOf(0),
                                    documentSnapshot.getString("name"),
                                    String.valueOf(documentSnapshot.getLong("totalScore")));
                            bestPlayerList.add(bestPlayer);
                        }
                        bestPlayerList.sort(Comparator.comparingLong(bestPlayer -> Long.parseLong(bestPlayer.getTotalScore())));
                        Collections.reverse(bestPlayerList);
                        for(BestPlayer bestPlayer : bestPlayerList) {
                            bestPlayer.setNumber(String.valueOf(indexNumber));
                            indexNumber++;
                        }
                        adapter = new PlayerAdapter(bestPlayerList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(BestPlayersActivity.this, "ERROR: FireStore related problem!", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}