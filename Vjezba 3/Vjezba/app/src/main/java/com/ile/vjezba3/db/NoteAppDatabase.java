package com.ile.vjezba3.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ile.vjezba3.entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteAppDatabase extends RoomDatabase {

    public abstract NoteDAO getNoteDAO();

}
