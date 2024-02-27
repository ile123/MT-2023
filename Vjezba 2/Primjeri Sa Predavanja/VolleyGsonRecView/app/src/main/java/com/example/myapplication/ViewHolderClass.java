package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderClass extends RecyclerView.ViewHolder {

    TextView name, occupation;

    public ViewHolderClass(@NonNull View itemView) {
        super(itemView);

        name = (TextView)itemView.findViewById(R.id.textView2);
        occupation = (TextView) itemView.findViewById(R.id.textView3);

    }

    public TextView getOccupation() {
        return occupation;
    }
    public TextView getName() {
        return name;
    }
}
