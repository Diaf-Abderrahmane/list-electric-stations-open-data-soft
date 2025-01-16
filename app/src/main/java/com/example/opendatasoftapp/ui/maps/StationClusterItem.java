package com.example.opendatasoftapp.ui.maps;

import com.example.opendatasoftapp.data.model.Result;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class StationClusterItem implements ClusterItem {

    private final Result station; // Your station object

    public StationClusterItem(Result station) {
        this.station = station;
    }

    @Override
    public LatLng getPosition() {
        // Return the position of the station
        return new LatLng(station.getMetaGeoPoint().getLat(), station.getMetaGeoPoint().getLon());
    }

    @Override
    public String getTitle() {
        // Return the name of the station (or any other title you prefer)
        return station.getMetaNameCom();
    }

    @Override
    public String getSnippet() {
        // Return a snippet with any additional information (e.g., access condition)
        return "Accès: " + station.getConditionAcces();
    }

    public Result getStation() {
        return station; // Return the station object if needed elsewhere
    }
}

