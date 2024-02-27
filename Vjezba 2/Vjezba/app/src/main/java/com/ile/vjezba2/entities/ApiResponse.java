package com.ile.vjezba2.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    @SerializedName("items")
    public List<Repository> repositories;

    public ApiResponse() {
        this.repositories = new ArrayList<Repository>();
    }

    @Override
    public String toString() {
        String allRepos = "";
        for (Repository repository : repositories){
            allRepos += ("Name: " + repository.getName() + "\tAvatar: " + repository.getAvatar() + "\tStars: " + repository.getStars() + "\n");
        }
        return allRepos;
    }
}
