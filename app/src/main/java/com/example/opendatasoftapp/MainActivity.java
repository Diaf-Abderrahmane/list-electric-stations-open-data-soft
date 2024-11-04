package com.example.opendatasoftapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Set the limit parameter to 2
        Call<ApiResponse> call = apiService.getGoldPrices(4);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.getResults() != null && !apiResponse.getResults().isEmpty()) {
                        for (Result result : apiResponse.getResults()) {
                            System.out.println("City: " + result.getMetaNameCom());
                            System.out.println("Department: " + result.getMetaNameDep());
                            System.out.println("Region: " + result.getMetaNameReg());
                            System.out.println("Access Condition: " + result.getConditionAcces());
                            if (result.getMetaGeoPoint() != null) {
                                System.out.println("Longitude: " + result.getMetaGeoPoint().getLon());
                                System.out.println("Latitude: " + result.getMetaGeoPoint().getLat());
                            }
                            System.out.println("-------------------");
                        }
                    } else {
                        System.out.println("No results available.");
                    }
                } else {
                    System.out.println("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });


    }
}