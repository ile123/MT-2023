package com.ile.vjezba3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ile.vjezba3.db.NoteAppDatabase;
import com.ile.vjezba3.entity.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    private NoteAppDatabase appDatabase;
    private Button submitButton;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        appDatabase = Room.databaseBuilder(getApplicationContext(), NoteAppDatabase.class,"NoteDB")
                .allowMainThreadQueries()
                .build();
        title = findViewById(R.id.addNoteTitle);
        content = findViewById(R.id.addNoteContent);
        submitButton = findViewById(R.id.addNoteSubmit);

        submitButton.setOnClickListener(v -> executorService.execute(() -> {
            Note note = new Note(0, title.getText().toString(), content.getText().toString());
            appDatabase.getNoteDAO().addNote(note);
            handler.post(() -> finish());
        }));
    }
}