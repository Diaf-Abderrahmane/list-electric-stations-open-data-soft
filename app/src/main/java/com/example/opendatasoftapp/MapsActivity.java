package com.example.opendatasoftapp;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.opendatasoftapp.R;
import com.example.opendatasoftapp.Result; // Assure-toi d'importer ta classe Result
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Result> stations; // Liste des stations

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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (stations != null) {
            // Ajoute un marqueur pour chaque station dans la liste
            for (Result station : stations) {
                if (station.getMetaGeoPoint() != null) {
                    LatLng location = new LatLng(
                            station.getMetaGeoPoint().getLat(),
                            station.getMetaGeoPoint().getLon()
                    );

                    mMap.addMarker(new MarkerOptions()
                            .position(location)
                            .title(station.getMetaNameCom()) // Nom de la commune
                            .snippet("Accès: " + station.getConditionAcces())); // Condition d'accès
                }
            }
            // Zoom sur la première station si la liste n'est pas vide
            if (!stations.isEmpty()) {
                Result firstStation = stations.get(0);
                if (firstStation.getMetaGeoPoint() != null) {
                    LatLng firstLocation = new LatLng(
                            firstStation.getMetaGeoPoint().getLat(),
                            firstStation.getMetaGeoPoint().getLon()
                    );
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 10));
                }
            }
        }
    }
}
