package com.ile.seminar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ile.seminar.R;
import com.ile.seminar.models.PlayerScore;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder>{

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView correctAnswers;
        public TextView finalScore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.playerScoreNumber);
            correctAnswers = itemView.findViewById(R.id.playerScoreNumberOfCorrectAnswers);
            finalScore = itemView.findViewById(R.id.playerScoreFinalScore);
        }
    }

    private final List<PlayerScore> playerScores;

    public ScoreAdapter(List<PlayerScore> playerScores) {
        this.playerScores = playerScores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_player_score_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PlayerScore score = playerScores.get(position);

        holder.number.setText(score.getNumber());
        holder.correctAnswers.setText(score.getCorrectAnswers());
        holder.finalScore.setText(score.getFinalScore());
    }

    @Override
    public int getItemCount() {
        return playerScores.size();
    }
}
