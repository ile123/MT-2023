package com.ile.obrana1.entities;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("items")
    public List<Country> countries;

    public ApiResponse(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder allCountries = new StringBuilder();
        for (Country country : countries){
            allCountries
                    .append("Name: ")
                    .append(country.getName())
                    .append("\tRegion: ")
                    .append(country.getRegion())
                    .append("\tCurrency: ")
                    .append(country.getCurrency())
                    .append("\n");
        }
        return allCountries.toString();
    }
}
