package com.example.opendatasoftapp.data.api;

import com.example.opendatasoftapp.data.model.Result;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("results")
    private List<Result> results;

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }
}
