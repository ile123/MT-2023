package com.ile.seminar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
import com.ile.seminar.models.BestPlayerMaxScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BestPlayersMaxScoreActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    List<BestPlayer> bestPlayerList = new ArrayList<>();
    List<BestPlayerMaxScore> bestPlayerMaxScoresList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_players_max_score);

        recyclerView = findViewById(R.id.bestPlayersMaxScoreRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CollectionReference collection = db.collection("users");

        collection.addSnapshotListener((value, error) -> collection
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        for (DocumentSnapshot documentSnapshot : task.getResult()) {
                            String playerId = documentSnapshot.getId();

                            CollectionReference scoresCollection = collection.document(playerId).collection("scores");

                            scoresCollection.get().addOnCompleteListener(scoresTask -> {
                                if (scoresTask.isSuccessful()) {
                                    boolean hasMaxScoreGame = false;
                                    long amountOfGames = 0;

                                    for (DocumentSnapshot scoreSnapshot : scoresTask.getResult()) {
                                        long scoreValue = scoreSnapshot.getLong("score");

                                        if (scoreValue == 1000) {
                                            hasMaxScoreGame = true;
                                            amountOfGames++;
                                        }
                                    }

                                    if (hasMaxScoreGame) {
                                        BestPlayerMaxScore bestPlayerMaxScore = new BestPlayerMaxScore(
                                                String.valueOf(0),
                                                documentSnapshot.getString("name"),
                                                String.valueOf(documentSnapshot.getLong("totalScore")),
                                                amountOfGames);
                                        BestPlayer bestPlayer = new BestPlayer(
                                                String.valueOf(0),
                                                documentSnapshot.getString("name"),
                                                String.valueOf(documentSnapshot.getLong("totalScore"))
                                        );

                                        bestPlayerList.add(bestPlayer);
                                        bestPlayerMaxScoresList.add(bestPlayerMaxScore);

                                        bestPlayerList.sort(Comparator.comparingLong(bp -> Long.parseLong(bp.getTotalScore())));
                                        Collections.reverse(bestPlayerList);

                                        bestPlayerMaxScoresList.sort(Comparator.comparingLong(BestPlayerMaxScore::getNumberOfGames));
                                        Collections.reverse(bestPlayerMaxScoresList);

                                        int indexNumber = 1;
                                        for (BestPlayer player : bestPlayerList) {
                                            player.setNumber(String.valueOf(indexNumber));
                                            indexNumber++;
                                        }

                                        for (BestPlayerMaxScore player : bestPlayerMaxScoresList) {
                                            player.setNumber(String.valueOf(indexNumber));
                                            indexNumber++;
                                        }

                                        adapter = new PlayerAdapter(bestPlayerList);
                                        recyclerView.setAdapter(adapter);
                                    }
                                } else {
                                    Toast.makeText(BestPlayersMaxScoreActivity.this, "ERROR: Firestore related problem!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(BestPlayersMaxScoreActivity.this, "ERROR: Firestore related problem!", Toast.LENGTH_SHORT).show();
                    }
                }));
        Log.d("Best Players: ", bestPlayerMaxScoresList.toString());
    }
}