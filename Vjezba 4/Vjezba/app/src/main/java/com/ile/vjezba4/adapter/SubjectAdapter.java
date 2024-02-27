package com.ile.vjezba4.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ile.vjezba4.R;
import com.ile.vjezba4.SubjectsFirestoreActivity;
import com.ile.vjezba4.entities.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView year;
        public TextView professor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.subjectName);
        }
        }
        private List<Subject> subjects;
        private SubjectsFirestoreActivity activity;
        public SubjectAdapter(List<Subject> subjects, SubjectsFirestoreActivity activity) {
            this.activity = activity;
            this.subjects = subjects;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.subject_item_layout, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final Subject subject = subjects.get(position);

            holder.name.setText(subject.getName());

            holder.itemView.setOnClickListener(v -> activity.goToEditSubject(subject.getId()));
        }

        @Override
        public int getItemCount() {
            return subjects.size();
        }
}
