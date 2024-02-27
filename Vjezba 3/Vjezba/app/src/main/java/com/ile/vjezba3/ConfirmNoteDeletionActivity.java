package com.ile.vjezba3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.ile.vjezba3.db.NoteAppDatabase;
import com.ile.vjezba3.entity.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConfirmNoteDeletionActivity extends AppCompatActivity {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    private NoteAppDatabase appDatabase;
    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_note_deletion);
        appDatabase = Room.databaseBuilder(getApplicationContext(), NoteAppDatabase.class,"NoteDB")
                .allowMainThreadQueries()
                .build();
        confirmButton = findViewById(R.id.deletionConfirmButton);
        cancelButton = findViewById(R.id.deletionCancelButton);
        Intent intent = getIntent();
        Note noteToDelete = appDatabase.getNoteDAO().getNote(Integer.parseInt(intent.getStringExtra("noteId")));
        confirmButton.setOnClickListener(v -> executorService.execute(() -> {
            appDatabase.getNoteDAO().deleteNote(noteToDelete);
            handler.post(() -> {
                Intent mainActivityIntent = new Intent(ConfirmNoteDeletionActivity.this, MainActivity.class);
                mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(mainActivityIntent);
                finish();
            });
        }));

        cancelButton.setOnClickListener(v -> {
            Intent editNoteIntent = new Intent(ConfirmNoteDeletionActivity.this, EditNoteActivity.class);
            editNoteIntent.putExtra("noteId", String.valueOf(noteToDelete.getId()));
            startActivity(editNoteIntent);
            finish();
        });
    }
}