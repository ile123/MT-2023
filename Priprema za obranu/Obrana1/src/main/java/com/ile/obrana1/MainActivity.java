package com.ile.obrana1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

    List<Country> countryList = new ArrayList<>();
    RecyclerView recyclerView;
    CountryAdapter countryAdapter;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.countryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "CountryDB")
                .allowMainThreadQueries()
                .build();
        if(db.getCountryDAO().getAllCountries().isEmpty()) {
            getCountries();
        } else {
            getCountriesInBackground();
        }
    }

    private void getCountries() {
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
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse result = response.body();
                db.getCountryDAO().insertAllCountries(result.countries);
                countryAdapter = new CountryAdapter(result.countries);
                recyclerView.setAdapter(countryAdapter);
                Log.d("RETROFIt: ", "USLO JE");
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("RETROFIT ERROR: ", "ERROR: Something went worng with it!");
            }
        });
    }

    private void getCountriesInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(() -> {
            countryList.addAll(db.getCountryDAO().getAllCountries());
            handler.post(() -> {
                countryAdapter = new CountryAdapter(countryList);
                recyclerView.setAdapter(countryAdapter);
                countryAdapter.notifyDataSetChanged();
                Log.d("ROOM: ", "USLO JE");
            });
        });
    }
}