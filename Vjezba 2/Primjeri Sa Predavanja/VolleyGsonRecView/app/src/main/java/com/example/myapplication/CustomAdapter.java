package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolderClass> {

    List<Person> localData;

    public CustomAdapter(List<Person> localData) {
        this.localData = localData;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent, false);

        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        holder.getName().setText(localData.get(position).getName());
        holder.getOccupation().setText(localData.get(position).getOccupation());
    }

    @Override
    public int getItemCount() {
        return localData.size();
    }
}
