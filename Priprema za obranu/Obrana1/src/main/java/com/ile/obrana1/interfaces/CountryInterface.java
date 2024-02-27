package com.ile.obrana1.interfaces;

import com.ile.obrana1.entities.ApiResponse;
import com.ile.obrana1.entities.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryInterface {
    @GET("countries")
    Call<ApiResponse> getAllCountries();
}
