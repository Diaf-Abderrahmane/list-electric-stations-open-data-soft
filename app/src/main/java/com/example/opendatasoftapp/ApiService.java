package com.example.opendatasoftapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("osm-france-charging-station/records")
    Call<ApiResponse> getStations(
            @Query("offset") int offset, // The starting point
            @Query("limit") int limit,   // Number of records to fetch
//            @Query("city") String city,
            @Query("where") String where // Region filter// City filter
    );
}
