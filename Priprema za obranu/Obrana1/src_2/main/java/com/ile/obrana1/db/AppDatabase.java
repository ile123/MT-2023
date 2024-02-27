package com.ile.obrana1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ile.obrana1.entities.Country;
import com.ile.obrana1.interfaces.CountryDAO;

@Database(entities = {Country.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDAO getCountryDAO();
}
