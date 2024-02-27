package com.ile.seminar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    Button playQuiz, playerScore, bestPlayers, bestPlayersMaxScore, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playQuiz = findViewById(R.id.playQuizButton);
        playerScore = findViewById(R.id.playerScoreButton);
        bestPlayers = findViewById(R.id.bestPlayersButton);
        bestPlayersMaxScore = findViewById(R.id.bestPlayersMaxScoreButton);
        logout = findViewById(R.id.logoutButton);

        playQuiz.setOnClickListener(v -> goToQuiz());

        playerScore.setOnClickListener(v -> goToPlayerScore());

        bestPlayers.setOnClickListener(v -> goToBestPlayers());

        bestPlayersMaxScore.setOnClickListener(v -> goToBestPlayersMaxScore());

        logout.setOnClickListener(v -> {
            auth.signOut();
            goToLogin();
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user == null) {
            goToLogin();
        }
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private void goToPlayerScore() {
        Intent intent = new Intent(this, PlayerScoreActivity.class);
        startActivity(intent);
    }

    private void goToBestPlayers() {
        Intent intent = new Intent(this, BestPlayersActivity.class);
        startActivity(intent);
    }

    private void goToBestPlayersMaxScore() {
        Intent intent = new Intent(this, BestPlayersMaxScoreActivity.class);
        startActivity(intent);
    }
}