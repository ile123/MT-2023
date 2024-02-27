package com.ile.seminar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuizResultActivity extends AppCompatActivity {

    TextView totalResult;
    ImageView celebrationGif;
    Button goToMainButton;
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        totalResult = findViewById(R.id.totalScoreResult);
        celebrationGif = findViewById(R.id.celebrationGif);
        goToMainButton = findViewById(R.id.goToMainButton);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        Intent intent = getIntent();
        long finalResult = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("amountOfCorrectAnswers")));
        long score = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("score")));
        long quizSize = Long.parseLong(Objects.requireNonNull(intent.getStringExtra("sizeOfQuiz")));

        totalResult.setText(finalResult + "/" + quizSize);

        if(finalResult < 1) {
            Glide.with(this).asGif().load(R.raw.zeroscore).into(celebrationGif);
        } else if(finalResult <= 4) {
            Glide.with(this).asGif().load(R.raw.lowscore).into(celebrationGif);
        } else if(finalResult == 5) {
            Glide.with(this).asGif().load(R.raw.mediumscore).into(celebrationGif);
        } else if (finalResult <= 9) {
            Glide.with(this).asGif().load(R.raw.highscore).into(celebrationGif);
        } else {
            Glide.with(this).asGif().load(R.raw.perfectscore).into(celebrationGif);
        }

        goToMainButton.setOnClickListener(v -> {
            Map<String, Long> data = new HashMap<>();
            data.put("score", score);
            data.put("correctAnswers", finalResult);
            DocumentReference userRef = db.collection("users").document(user.getUid());
            CollectionReference scoresCollectionRef = userRef.collection("scores");
            updateTotalScoreAndAddScore(userRef, scoresCollectionRef, data);
        });
    }

    private void updateTotalScoreAndAddScore(DocumentReference userRef, CollectionReference scoresCollectionRef, Map<String, Long> data) {
        db.runTransaction((Transaction.Function<Void>) transaction -> {
                    DocumentSnapshot snapshot = transaction.get(userRef);
                    long currentTotalScore = snapshot.getLong("totalScore");
                    long newTotalScore = currentTotalScore + data.get("score");
                    transaction.update(userRef, "totalScore", newTotalScore);
                    scoresCollectionRef.add(data);
                    return null;
                })
                .addOnSuccessListener(result -> finish())
                .addOnFailureListener(e -> Log.e("Firestore:", "Error updating totalScore and adding score document", e));
    }
}