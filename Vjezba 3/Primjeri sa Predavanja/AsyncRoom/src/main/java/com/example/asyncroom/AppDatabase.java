package com.example.asyncroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities= {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}
