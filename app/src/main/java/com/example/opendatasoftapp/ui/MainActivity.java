package com.example.opendatasoftapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.opendatasoftapp.data.api.ApiClient;
import com.example.opendatasoftapp.data.api.ApiResponse;
import com.example.opendatasoftapp.data.api.ApiService;
import com.example.opendatasoftapp.ui.maps.MapsActivity;
import com.example.opendatasoftapp.R;
import com.example.opendatasoftapp.data.model.Result;
import com.example.opendatasoftapp.Utils.AlertUtils;
import com.example.opendatasoftapp.Utils.NetworkUtils;
import com.example.opendatasoftapp.ui.adapters.RecordAdapter;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    private List<Result> stationList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecordAdapter adapter;

    // Variables for pagination
    private int offset = 0; // Starting point for fetching data
    private final int limit = 20; // Number of records per load
    private boolean isLoading = false;
    private Spinner spinnerGratuit;
    private Spinner spinnerRegion;
    private Button applyFiltersButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the drawer layout and navigation view
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
                Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(homeIntent);
            } else if (itemId == R.id.nav_favorites) {
                // Navigate to Favorites
                Intent favoritesIntent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(favoritesIntent);
            }
            drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer
            return true;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        applyFiltersButton = findViewById(R.id.apply_button);
        spinnerGratuit = findViewById(R.id.spinner_gratuit);
        spinnerRegion = findViewById(R.id.region_spinner);

//        fetchCities();
        fetchRegions();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecordAdapter(stationList, this);
        recyclerView.setAdapter(adapter);

        // RecyclerView Pagination to handle scrolling
        // Add scroll listener for pagination
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading && layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == stationList.size() - 1) {
                    String selectedRegion = spinnerRegion.getSelectedItem() != null ? spinnerRegion.getSelectedItem().toString() : "";
                    String selectedGratuit = spinnerGratuit.getSelectedItem().toString();
                    if (selectedRegion.equals("Région")) {
                        fetchRecords("", selectedGratuit);
                    } else {
                        fetchRecords(selectedRegion, selectedGratuit);
                    } // Fetch more records when scrolled to the bottom
                }
            }
        });


        // Initial API call to fetch stations
        checkAndCallApi();




        // Set up the apply filters button
        applyFiltersButton.setOnClickListener(v -> applyFilters());

        // Navigate to maps activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MapsActivity with the station list
                if (stationList != null && !stationList.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putParcelableArrayListExtra("stations_key", new ArrayList<>(stationList));
                    startActivity(intent);
                } else {
                    // Handle case where stationList is null or empty
                    System.out.println("No stations available to show on map.");
                }
            }
        });

    }


    // fetch with gratuit
    private void fetchRecords(String region, String gratuit) {
        // Build the whereClause dynamically
        StringBuilder whereClause = new StringBuilder();

        // Add region filter if applicable
        if (region != null && !region.isEmpty()) {
            whereClause.append("meta_name_reg=\"").append(region).append("\"");
        }

        // Add gratuit filter if applicable
        if (gratuit != null && !gratuit.equals("All")) {
            if (whereClause.length() > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append("gratuit=\"").append(gratuit.equals("Gratuit") ? "true" : "false").append("\"");
        }

        String whereClauseFinal = whereClause.length() > 0 ? whereClause.toString() : null;

        if (isLoading) return; // Prevents multiple calls while loading

        isLoading = true;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<ApiResponse> call = apiService.getStations(offset, limit, whereClauseFinal);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                isLoading = false;
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();

                    if (apiResponse.getResults() != null && !apiResponse.getResults().isEmpty()) {
                        List<Result> results = apiResponse.getResults();

                        int previousSize = stationList.size();
                        stationList.addAll(results);
                        adapter.notifyItemRangeInserted(previousSize, results.size());
                        offset += results.size();
                    } else {
                        System.out.println("No results available.");
                    }
                } else {
                    System.out.println("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                isLoading = false; // Reset loading state
                System.out.println("Error: " + t.getMessage());
            }
        });
    }


    private void fetchRegions() {
        spinnerRegion = findViewById(R.id.region_spinner);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        // You can call your existing API here to fetch the data
        Call<ApiResponse> call = apiService.getStations(0, 100, null); // You can increase the limit for more data
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<String> regionList = new ArrayList<>();
                    regionList.add("Région");

                    for (Result station : apiResponse.getResults()) {
                        String region = station.getMetaNameReg();  // Assuming this is how to get the city name
                        if (!regionList.contains(region)) {
                            regionList.add(region);
                        }
                    }

                    // Now populate the Spinner with the city list
                    ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, regionList);
                    regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRegion.setAdapter(regionAdapter);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    // apply fitlers with gratuit
    private void applyFilters() {
        // Get selected region
        String selectedRegion = spinnerRegion.getSelectedItem() != null ? spinnerRegion.getSelectedItem().toString() : null;

        // Get selected gratuit filter
        String selectedGratuit = spinnerGratuit.getSelectedItem() != null ? spinnerGratuit.getSelectedItem().toString() : null;

        // Handle case when no region is selected
        if (selectedRegion == null || selectedRegion.isEmpty()) {
            System.out.println("No Region selected.");
            return;
        }

        // Clear existing data before fetching new filtered data
        stationList.clear();
        adapter.notifyDataSetChanged();
        offset = 0; // Reset offset for fresh fetch

        // Handle "None" or default selection for region
        if ("Région".equals(selectedRegion)) selectedRegion = "";

        // Call fetchRecords with both filters
        fetchRecords(selectedRegion, selectedGratuit);
    }

    private void checkAndCallApi() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            // Show the dialog and retry API call on "Retry"
            AlertUtils.showNoInternetDialog(this, new Runnable() {
                @Override
                public void run() {
                    // Retry logic
                    checkAndCallApi();
                }
            });
        } else {
            // Fetching the stations using Retrofit
            fetchRecords("", "All");
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

