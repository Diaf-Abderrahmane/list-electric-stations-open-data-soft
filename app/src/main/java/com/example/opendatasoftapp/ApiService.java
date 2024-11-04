package com.example.opendatasoftapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("osm-france-charging-station/records")
    Call<ApiResponse> getGoldPrices(@Query("limit") int limit);
}