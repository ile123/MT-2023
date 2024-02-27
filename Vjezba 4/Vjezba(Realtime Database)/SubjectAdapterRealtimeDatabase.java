package com.ile.vjezba4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ile.vjezba4.R;
import com.ile.vjezba4.SubjectRealtimeDatabaseActivity;
import com.ile.vjezba4.entities.Subject;

import java.util.List;

public class SubjectAdapterRealtimeDatabase extends RecyclerView.Adapter<SubjectAdapterRealtimeDatabase.MyViewHolder>{
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
    private SubjectRealtimeDatabaseActivity activity;
    public SubjectAdapterRealtimeDatabase(List<Subject> subjects, SubjectRealtimeDatabaseActivity activity) {
        this.activity = activity;
        this.subjects = subjects;
    }
    @NonNull
    @Override
    public SubjectAdapterRealtimeDatabase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_item_layout, parent, false);
        return new SubjectAdapterRealtimeDatabase.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapterRealtimeDatabase.MyViewHolder holder, int position) {
        final Subject subject = subjects.get(position);

        holder.name.setText(subject.getName());

        holder.itemView.setOnClickListener(v -> activity.goToEditSubject(subject.getId()));
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}
