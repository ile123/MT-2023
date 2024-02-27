package com.ile.seminar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ile.seminar.R;
import com.ile.seminar.models.BestPlayer;
import com.ile.seminar.models.PlayerScore;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView name;
        public TextView totalScore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.bestPlayersNumber);
            name = itemView.findViewById(R.id.bestPlayersName);
            totalScore = itemView.findViewById(R.id.bestPlayersTotalScore);
        }
    }

    private final List<BestPlayer> bestPlayers;

    public PlayerAdapter(List<BestPlayer> bestPlayers) {
        this.bestPlayers = bestPlayers;
    }

    @NonNull
    @Override
    public PlayerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_best_players_item, parent, false);
        return new PlayerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.MyViewHolder holder, int position) {
        final BestPlayer bestPlayer = bestPlayers.get(position);

        holder.number.setText(bestPlayer.getNumber());
        holder.name.setText(bestPlayer.getName());
        holder.totalScore.setText(bestPlayer.getTotalScore());
    }

    @Override
    public int getItemCount() {
        return bestPlayers.size();
    }
}
