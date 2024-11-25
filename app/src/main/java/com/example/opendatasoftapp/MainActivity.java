package com.example.opendatasoftapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Result> stationList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecordAdapter adapter;

    // Variables for pagination
    private int offset = 0; // Starting point for fetching data
    private final int limit = 20; // Number of records per load
    private boolean isLoading = false;
    private Spinner spinnerCity;
    private Spinner spinnerRegion;
    private Button applyFiltersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        applyFiltersButton = findViewById(R.id.apply_button);
        spinnerCity = findViewById(R.id.spinner_city);
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
                    String selectedRegion = spinnerRegion.getSelectedItem().toString();
                    if (selectedRegion.equals("None")) {
                    fetchRecords("");} else {
                        fetchRecords(selectedRegion);
                    } // Fetch more records when scrolled to the bottom
                }
            }
        });

        // Fetching the stations using Retrofit
        fetchRecords("");

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


    private void fetchRecords(String region){
        String whereClause = (region == null || region.isEmpty()) ? null : "meta_name_reg=\"" + region + "\"";
        if (isLoading) return; // Prevents multiple calls while loading

        isLoading = true;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        System.out.println("Fetching records for city: " + city);

        Call<ApiResponse> call = apiService.getStations(offset, limit, whereClause);
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
                        offset+= results.size();
//                        RecordAdapter adapter = new RecordAdapter(stationList, MainActivity.this);
//                        recyclerView.setAdapter(adapter);
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

//    private void fetchCities() {
//        spinnerCity = findViewById(R.id.spinner_city);
//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        // You can call your existing API here to fetch the data
//        Call<ApiResponse> call = apiService.getStations(0, 100,null, null); // You can increase the limit for more data
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    ApiResponse apiResponse = response.body();
//                    List<String> cityList = new ArrayList<>();
//
//                    for (Result station : apiResponse.getResults()) {
//                        String city = station.getMetaNameCom();  // Assuming this is how to get the city name
//                        if (!cityList.contains(city)) {
//                            cityList.add(city);
//                        }
//                    }
//
//                    // Now populate the Spinner with the city list
//                    ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, cityList);
//                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinnerCity.setAdapter(cityAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                System.out.println("Error: " + t.getMessage());
//            }
//        });
//    }

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
                    regionList.add("None");

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


    private void applyFilters() {

//        String selectedCity = spinnerCity.getSelectedItem() != null ? spinnerCity.getSelectedItem().toString() : null;
        String selectedRegion = spinnerRegion.getSelectedItem() != null ? spinnerRegion.getSelectedItem().toString() : null;

//        if (selectedCity == null || selectedCity.isEmpty()) {
//            System.out.println("No city selected.");
//            return;
//        }

        if (selectedRegion == null || selectedRegion.isEmpty()) {
            System.out.println("No Region selected.");
            return;
        }

        // Apply filters based on selected values
        // You can either pass these filters to your API or filter locally
        // If 'All' is selected, we ignore the filter for that field.
        // Clear existing data before fetching new filtered data
        stationList.clear();
        adapter.notifyDataSetChanged();
        offset = 0; // Reset offset for a fresh fetch


        fetchRecords(selectedRegion);
    }





}


//private void fetchRecords(){
//    isLoading = true;
//    ApiService apiService = ApiClient.getClient().create(ApiService.class);
//
//    Call<ApiResponse> call = apiService.getGoldPrices(offset, limit);
//    call.enqueue(new Callback<ApiResponse>() {
//        @Override
//        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//            if (response.isSuccessful() && response.body() != null) {
//                ApiResponse apiResponse = response.body();
//                if (apiResponse.getResults() != null && !apiResponse.getResults().isEmpty()) {
//                    stationList = apiResponse.getResults();
//                    RecordAdapter adapter = new RecordAdapter(stationList, MainActivity.this);
//                    recyclerView.setAdapter(adapter);
//                } else {
//                    System.out.println("No results available.");
//                }
//            } else {
//                System.out.println("Request failed with code: " + response.code());
//            }
//        }
//
//        @Override
//        public void onFailure(Call<ApiResponse> call, Throwable t) {
//            System.out.println("Error: " + t.getMessage());
//        }
//    });
//}
//
//private void loadMoreStations() {
//
//}