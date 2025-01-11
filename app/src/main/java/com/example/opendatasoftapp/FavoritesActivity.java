package com.example.opendatasoftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView favoritesRecyclerView;
    private RecordAdapter adapter;
    private List<Result> favoriteStations = new ArrayList<>(); // List to hold favorited stations

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar_favorites);
        setSupportActionBar(toolbar);

        // Enable the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Handle navigation item clicks
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                // Navigate to Home
                Intent homeIntent = new Intent(FavoritesActivity.this, MainActivity.class);
                startActivity(homeIntent);
            } else if (itemId == R.id.nav_favorites) {
                // Navigate to Favorites
                Intent favoritesIntent = new Intent(FavoritesActivity.this, FavoritesActivity.class);
                startActivity(favoritesIntent);
            }
            drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer
            return true;
        });

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {// Naviguer vers l'écran d'accueil
            // Par exemple, avec un Intent ou en changeant de fragment
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (itemId == R.id.nav_favorites) {// Naviguer vers l'écran des favoris
            startActivity(new Intent(this, FavoritesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



