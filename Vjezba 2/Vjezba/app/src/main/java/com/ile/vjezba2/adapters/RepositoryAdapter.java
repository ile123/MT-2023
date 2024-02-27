package com.ile.vjezba2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ile.vjezba2.R;
import com.ile.vjezba2.entities.Repository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView repositoryName;
        public TextView repositoryStars;
        public ImageView repositoryImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repositoryName = itemView.findViewById(R.id.repositoryName);
            repositoryStars = itemView.findViewById(R.id.repositoryStars);
            repositoryImage = itemView.findViewById(R.id.repositoryImage);
        }
    }

    private List<Repository> repositories;

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repositoryView = inflater.inflate(R.layout.repository_item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(repositoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repository = repositories.get(position);

        TextView repositoryName = holder.repositoryName;
        TextView repositoryStars = holder.repositoryStars;
        ImageView repositoryImage = holder.repositoryImage;

        repositoryName.setText(repository.getName());
        repositoryStars.setText(String.valueOf(repository.getStars()));
        Glide.with(holder.itemView.getContext())
                .load(repository.getAvatar())
                .into(repositoryImage);
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
