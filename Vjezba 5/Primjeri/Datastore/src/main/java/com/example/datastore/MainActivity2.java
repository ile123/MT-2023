package com.example.datastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import android.os.Bundle;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class MainActivity2 extends AppCompatActivity {

    RxDataStore<Preferences> dataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dataStore = new RxPreferenceDataStoreBuilder(this, "data").build();
        Preferences.Key<String> PREF_KEY = PreferencesKeys.stringKey("name");
        Single<Preferences> updateResult = dataStore.updateDataAsync(new Function<Preferences, Single<Preferences>>() {
            @Override
            public Single<Preferences> apply(Preferences preferences) throws Exception {
                MutablePreferences mutablePreferences = preferences.toMutablePreferences();
                mutablePreferences.set(PREF_KEY, "Engleski");
                return Single.just(mutablePreferences);
            }
        });
    }
}