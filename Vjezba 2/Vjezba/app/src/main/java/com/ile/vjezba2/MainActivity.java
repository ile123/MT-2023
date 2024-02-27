package com.ile.vjezba2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ile.vjezba2.adapters.RepositoryAdapter;
import com.ile.vjezba2.entities.ApiResponse;
import com.ile.vjezba2.entities.Repository;
import com.ile.vjezba2.interfaces.GithubInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Repository> repositories = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recicleViewRepository);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*List<Repository> mockData = new ArrayList<Repository>()
        {
            {
                add(new Repository("freeCodeCamp", 377846, "https://avatars.githubusercontent.com/u/9892522?v=4"));
                add(new Repository("free-programming-books", 302967, "https://avatars.githubusercontent.com/u/14127308?v=4"));
                add(new Repository("awesome", 276775, "https://avatars.githubusercontent.com/u/170270?v=4"));
                add(new Repository("coding-interview-university", 268136, "https://avatars.githubusercontent.com/u/3771963?v=4"));
                add(new Repository("public-apis", 268132, "https://avatars.githubusercontent.com/u/48942249?v=4"));
                add(new Repository("developer-roadmap", 228192, "https://avatars.githubusercontent.com/u/4921183?v=4"));
                add(new Repository("system-design-primer", 218192, "https://avatars.githubusercontent.com/u/5458997?v=4"));
                add(new Repository("build-your-own-x", 212192, "https://avatars.githubusercontent.com/u/58904235?v=4"));
                add(new Repository("react", 210192, "https://avatars.githubusercontent.com/u/69631?v=4"));
                add(new Repository("awesome-python", 208192, "https://avatars.githubusercontent.com/u/652070?v=4"));
            }
        };*/
        getRepositories();
}

public void getRepositories() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        GithubInterface api = retrofit.create(GithubInterface.class);
        Call<ApiResponse> call = api.getAllRepositoriesByCriteria("stars:>100000");

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse result = response.body();
                RepositoryAdapter adapter = new RepositoryAdapter(result.repositories);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("RETROFIT ERROR: ", t.getMessage());
            }
        });
    }
}