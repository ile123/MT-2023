package com.ile.obrana1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.ile.obrana1.adapters.CountryAdapter;
import com.ile.obrana1.db.AppDatabase;
import com.ile.obrana1.entities.ApiResponse;
import com.ile.obrana1.entities.Country;
import com.ile.obrana1.interfaces.CountryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Country> countries = new ArrayList<>();
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    CountryAdapter adapter;
    AppDatabase db;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.countriesRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"CountryDB")
                .allowMainThreadQueries()
                .build();
        if(db.getCountryDAO().getAll().isEmpty()) {
            countries = getCountriesFromMockable();
            db.getCountryDAO().insertAll(countries);
        } else {
            getAllCountriesFromDB();
        }
        adapter = new CountryAdapter(countries);
        recyclerView.setAdapter(adapter);
    }

    List<Country> getCountriesFromMockable() {
        List<Country> countryList = new ArrayList<>();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://demo7777620.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        CountryInterface api = retrofit.create(CountryInterface.class);
        Call<ApiResponse> call = api.getAllCountries();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse result = response.body();
                countryList.addAll(result.countries);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, Throwable t) {
                Log.d("RETROFIT ERROR: ", t.getMessage());
            }
        });
        return countryList;
    }

    void getAllCountriesFromDB() {
        executorService.execute(() -> {
            countries.addAll(db.getCountryDAO().getAll());
            handler.post(() -> adapter.notifyDataSetChanged());
        });
    }
}