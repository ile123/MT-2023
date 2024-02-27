package com.example.datastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    RxDataStore<Preferences> dataStore;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataStore = new RxPreferenceDataStoreBuilder(this, "data").build();
        button = findViewById(R.id.button);
        Preferences.Key<String> PREF_KEY = PreferencesKeys.stringKey("name");
        Single<Preferences> updateResult = dataStore.updateDataAsync(new Function<Preferences, Single<Preferences>>() {
            @Override
            public Single<Preferences> apply(Preferences preferences) throws Exception {
                MutablePreferences mutablePreferences = preferences.toMutablePreferences();
                mutablePreferences.set(PREF_KEY, "Marina ");
                return Single.just(mutablePreferences);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }


}