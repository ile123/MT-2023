package com.ile.obrana1.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    //data: [{name: "Croatia", region: "Balkan", currency: "EUR"}, {...}, ...]
    @SerializedName("data")
    public List<Country> countries;
}
