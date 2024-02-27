package com.ile.vjezba3.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @ColumnInfo(name = "notes_id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "notes_title")
    private String title;
    @ColumnInfo(name = "notes_content")
    private String content;

    @Ignore
    public Note() {

    }

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title: " + this.id + "\nTitle: " + this.title + "\nContent: " + this.content;
    }
}
