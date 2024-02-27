package com.example.asyncroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "korisnik")
public class User {
    public User(String name, String occupation){
        this.name = name;
        this.occupation = occupation;
    }

    @PrimaryKey(autoGenerate = true)
    public  int uid;

    @ColumnInfo(name="first_name")
    public String name;

    @ColumnInfo(name="users_occupation")
    public String occupation;
}
