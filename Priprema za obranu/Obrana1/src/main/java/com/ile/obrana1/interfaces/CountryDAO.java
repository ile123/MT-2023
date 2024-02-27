package com.ile.obrana1.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ile.obrana1.entities.Country;

import java.util.List;

@Dao
public interface CountryDAO {
    @Query("SELECT * FROM countries")
    List<Country> getAllCountries();

    @Insert
    void insertAllCountries(List<Country> countries);
}
