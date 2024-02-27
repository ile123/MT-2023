package com.ile.vjezba2.interfaces;

import com.ile.vjezba2.entities.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubInterface {
    @GET("search/repositories")
    Call<ApiResponse> getAllRepositoriesByCriteria(@Query("q")String query);
}
