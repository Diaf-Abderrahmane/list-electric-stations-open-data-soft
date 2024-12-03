package com.example.opendatasoftapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesRecyclerView;
    private RecordAdapter adapter;
    private List<Result> favoriteStations = new ArrayList<>(); // List to hold favorited stations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load favorite stations from SharedPreferences
        loadFavorites();

        // Set up the adapter
        adapter = new RecordAdapter(favoriteStations, this);
        favoritesRecyclerView.setAdapter(adapter);
    }

    private void loadFavorites() {
        SharedPreferences preferences = getSharedPreferences("favorites", Context.MODE_PRIVATE);
        Set<String> favoriteStationsJson = preferences.getStringSet("favoriteStations", new HashSet<>());

        Gson gson = new Gson();

        for (String stationJson : favoriteStationsJson) {
            // Deserialize the JSON string back into a Result object
            Result station = gson.fromJson(stationJson, Result.class);
            favoriteStations.add(station);  // Add the station to the list
        }
    }
}

