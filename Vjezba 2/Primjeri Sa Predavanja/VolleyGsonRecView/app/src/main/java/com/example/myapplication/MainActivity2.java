package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity2 extends AppCompatActivity {
    PersonList personList;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doRequest();
    }

    public void doRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://demo7168568.mockable.io/genres";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                personList = new PersonList();
                Gson gson = new GsonBuilder().create();
                personList = gson.fromJson(response, PersonList.class);

                adapter = new CustomAdapter(personList.osobe);
                recyclerView.setAdapter(adapter);

                Log.d("error", personList.toString());










            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", error.toString());

            }
        });
        queue.add(stringRequest);
    }
}