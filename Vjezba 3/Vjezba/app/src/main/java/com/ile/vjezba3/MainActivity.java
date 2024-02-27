package com.ile.vjezba3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.HandlerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ile.vjezba3.adapter.NoteAdapter;
import com.ile.vjezba3.db.NoteAppDatabase;
import com.ile.vjezba3.entity.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    private NoteAdapter noteAdapter;
    private List<Note> noteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NoteAppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.noteToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes");

        recyclerView = findViewById(R.id.noteRecycleView);
        RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                Log.i("DB", "Database has been opened");
            }
        };

        appDatabase = Room.databaseBuilder(getApplicationContext(), NoteAppDatabase.class,"NoteDB")
                .addCallback(myCallback)
                .build();

        DisplayAllNotesInBackground();

        noteAdapter = new NoteAdapter(noteList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(noteAdapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.noteFloatingButton);
        floatingActionButton.setOnClickListener(v -> createNote());
    }

    @Override
    protected void onResume() {
        super.onResume();

        executorService.execute(() -> {
            List<Note> notes = appDatabase.getNoteDAO().getNotes();
            handler.post(() -> {
                noteAdapter = new NoteAdapter(notes, MainActivity.this);
                noteList = notes;
                recyclerView.setAdapter(noteAdapter);
                noteAdapter.notifyDataSetChanged();
            });
        });
    }

    private void createNote() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    public void editNote(final int noteId) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("noteId", String.valueOf(noteId));
        startActivity(intent);
    }

    private void DisplayAllNotesInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            noteList.addAll(appDatabase.getNoteDAO().getNotes());
            handler.post(() -> noteAdapter.notifyDataSetChanged());
        });
    }
}