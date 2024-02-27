package com.ile.vjezba3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ile.vjezba3.db.NoteAppDatabase;
import com.ile.vjezba3.entity.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditNoteActivity extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    private NoteAppDatabase appDatabase;
    private Button submitButton;
    private Button deleteButton;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        appDatabase = Room.databaseBuilder(getApplicationContext(), NoteAppDatabase.class,"NoteDB")
                .allowMainThreadQueries()
                .build();
        title = findViewById(R.id.editNoteTitle);
        content = findViewById(R.id.editNoteContent);
        submitButton = findViewById(R.id.editNoteSubmit);
        deleteButton = findViewById(R.id.editNoteDelete);

        Intent intent = getIntent();
        Note noteToEdit = appDatabase.getNoteDAO().getNote(Integer.parseInt(intent.getStringExtra("noteId")));
        title.setText(noteToEdit.getTitle());
        content.setText(noteToEdit.getContent());

        submitButton.setOnClickListener(v -> executorService.execute(() -> {
            noteToEdit.setTitle(title.getText().toString());
            noteToEdit.setContent(content.getText().toString());
            appDatabase.getNoteDAO().updateNote(noteToEdit);
            handler.post(() -> finish());
        }));

        deleteButton.setOnClickListener(v -> goToConfirmationScreen(noteToEdit.getId()));
    }

    private void goToConfirmationScreen(int noteId) {
        Intent intent = new Intent(this, ConfirmNoteDeletionActivity.class);
        intent.putExtra("noteId", String.valueOf(noteId));
        startActivity(intent);
    }
}