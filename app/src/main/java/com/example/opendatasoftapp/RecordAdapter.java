package com.example.opendatasoftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private List<Result> records;
    private Context context;

    public RecordAdapter(List<Result> records, Context context) {
        this.records = records;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Result record = records.get(position);
        holder.tvMetaNameCom.setText(record.getMetaNameCom());
        holder.tvMetaNameDep.setText(record.getMetaNameDep());
        holder.tvConditionAcces.setText(record.getConditionAcces());
        holder.tvGratuit.setText(String.format("Gratuit: %s", record.getGratuit()));

        // Check if the station is in favorites and update the heart icon
        if (isFavorite(record)) {
            holder.favoriteButton.setImageResource(R.drawable.heart_filled_icon);  // Filled heart
        } else {
            holder.favoriteButton.setImageResource(R.drawable.heart_stroke_icon);  // Empty heart
        }

        // Handle click event on the favorite button
        holder.favoriteButton.setOnClickListener(v -> {
            if (isFavorite(record)) {
                removeFromFavorites(record);  // Remove from favorites
                holder.favoriteButton.setImageResource(R.drawable.heart_stroke_icon);  // Empty heart
            } else {
                addToFavorites(record);  // Add to favorites
                holder.favoriteButton.setImageResource(R.drawable.heart_filled_icon);  // Filled heart
            }
        });

        holder.itemView.setOnClickListener(v -> {
            // Start DetailActivity with the selected Result object
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("result", record); // Pass the Result object
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return records != null ? records.size() : 0;
    }

    private void addToFavorites(Result station) {
        SharedPreferences preferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String stationJson = gson.toJson(station);
        Log.d("FavoritesDebug", "Adding station JSON: " + stationJson);

        Set<String> favorites = new HashSet<>(preferences.getStringSet("favoriteStations", new HashSet<>()));
        favorites.add(stationJson);
        editor.putStringSet("favoriteStations", favorites);
        editor.apply();

        Log.d("FavoritesDebug", "Favorites after adding: " + favorites);
    }

    private void removeFromFavorites(Result station) {
        SharedPreferences preferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String stationJson = gson.toJson(station);
        Log.d("FavoritesDebug", "Removing station JSON: " + stationJson);

        Set<String> favorites = new HashSet<>(preferences.getStringSet("favoriteStations", new HashSet<>()));
        favorites.remove(stationJson);
        editor.putStringSet("favoriteStations", favorites);
        editor.apply();

        Log.d("FavoritesDebug", "Favorites after removing: " + favorites);
    }

    private boolean isFavorite(Result station) {
        SharedPreferences preferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        Set<String> favorites = preferences.getStringSet("favoriteStations", new HashSet<>());

        Gson gson = new Gson();
        String stationJson = gson.toJson(station);
        Log.d("FavoritesDebug", "Checking if favorite. Station JSON: " + stationJson);
        Log.d("FavoritesDebug", "Favorites: " + favorites);

        return favorites.contains(stationJson);
    }



    static class RecordViewHolder extends RecyclerView.ViewHolder {
        TextView tvMetaNameCom, tvMetaNameDep, tvConditionAcces, tvGratuit;
        ImageView favoriteButton;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMetaNameCom = itemView.findViewById(R.id.tvMetaNameCom);
            tvMetaNameDep = itemView.findViewById(R.id.tvMetaNameDep);
            tvConditionAcces = itemView.findViewById(R.id.tvConditionAcces);
            tvGratuit = itemView.findViewById(R.id.tvGratuit);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }

    public void updateRecords(List<Result> newRecords) {
        records = newRecords;
        notifyDataSetChanged();
    }
}

