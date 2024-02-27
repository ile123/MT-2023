package com.ile.vjezba3.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ile.vjezba3.entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    long addNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM notes")
    List<Note> getNotes();

    @Query("SELECT * FROM notes WHERE notes_id ==:noteId")
    Note getNote(long noteId);
}
