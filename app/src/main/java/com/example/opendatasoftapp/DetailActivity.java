package com.example.opendatasoftapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView cityDetailTextView = findViewById(R.id.cityDetailTextView);
        TextView departmentDetailTextView = findViewById(R.id.departmentDetailTextView);
        TextView regionDetailTextView = findViewById(R.id.regionDetailTextView);
        TextView accessConditionDetailTextView = findViewById(R.id.accessConditionDetailTextView);
        TextView longitudeTextView = findViewById(R.id.longitudeTextView);
        TextView latitudeTextView = findViewById(R.id.latitudeTextView);

        // Retrieve the Result object from the Intent
        Result result = (Result) getIntent().getParcelableExtra("result");

        if (result != null) {
            cityDetailTextView.setText(String.format("City: %s", result.getMetaNameCom()));
            departmentDetailTextView.setText("Department: " + result.getMetaNameDep());
            regionDetailTextView.setText("Region: " + result.getMetaNameReg());
            accessConditionDetailTextView.setText("Access Condition: " + result.getConditionAcces());

            if (result.getMetaGeoPoint() != null) {
                longitudeTextView.setText("Longitude: " + result.getMetaGeoPoint().getLon());
                latitudeTextView.setText("Latitude: " + result.getMetaGeoPoint().getLat());
            }
        }
    }
}
