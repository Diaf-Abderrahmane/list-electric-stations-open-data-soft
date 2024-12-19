package com.example.opendatasoftapp;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.opendatasoftapp.R;
import com.example.opendatasoftapp.Result; // Assure-toi d'importer ta classe Result
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Result> stations; // Liste des stations
    private ClusterManager<StationClusterItem> clusterManager; // Cluster manager for clustering markers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Récupère la liste des stations depuis l'intent
        stations = getIntent().getParcelableArrayListExtra("stations_key");

        // Initialiser la carte
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @SuppressLint("PotentialBehaviorOverride")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Initialize the cluster manager
        clusterManager = new ClusterManager<>(this, mMap);

        // Set the custom info window for the markers
        mMap.setOnMarkerClickListener(clusterManager);

        if (stations != null) {
            // Ajoute un marqueur pour chaque station dans la liste
            for (Result station : stations) {
                if (station.getMetaGeoPoint() != null) {
                    // Create a new StationClusterItem for each station
                    StationClusterItem item = new StationClusterItem(station);
                    clusterManager.addItem(item);

                }
            }

            // Set camera idle listener to update clusters after camera move
            mMap.setOnCameraIdleListener(clusterManager);

//             Zoom on the first station if the list is not empty
            if (!stations.isEmpty()) {
                Result firstStation = stations.get(0);
                if (firstStation.getMetaGeoPoint() != null) {
                    LatLng firstLocation = new LatLng(
                            firstStation.getMetaGeoPoint().getLat(),
                            firstStation.getMetaGeoPoint().getLon()
                    );
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 8));
                }
            }

        }
    }
}
