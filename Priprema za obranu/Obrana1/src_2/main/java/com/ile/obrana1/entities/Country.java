package com.ile.obrana1.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class Country {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "country_name")
    public String name;
    @ColumnInfo(name = "country_region")
    public String region;
    @ColumnInfo(name = "country_currency")
    public String currency;

    public Country() {

    }

    public Country(String name, String region, String currency) {
        this.name = name;
        this.region = region;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
