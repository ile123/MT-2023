package com.ile.obrana1.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class Country {
    @ColumnInfo(name = "countries_id")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "countries_name")
    public String name;
    @ColumnInfo(name = "countries_region")
    public String region;
    @ColumnInfo(name = "countries_currency")
    public String currency;

    public Country() {

    }

    public Country(String name, String region, String currency) {
        this.name = name;
        this.region = region;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCurrency() {
        return currency;
    }
}
