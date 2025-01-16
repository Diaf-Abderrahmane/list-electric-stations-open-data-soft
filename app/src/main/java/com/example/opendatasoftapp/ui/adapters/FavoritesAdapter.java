package com.example.opendatasoftapp.ui.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.opendatasoftapp.R;
import com.example.opendatasoftapp.data.model.Result;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {
    private List<Result> favoriteStations;
    private Context context;

    public FavoritesAdapter(List<Result> favoriteStations, Context context) {
        this.favoriteStations = favoriteStations;
        this.context = context;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Result station = favoriteStations.get(position);
        holder.tvMetaNameCom.setText(station.getMetaNameCom());
        holder.tvMetaNameDep.setText(station.getMetaNameDep());
        holder.tvConditionAcces.setText(station.getConditionAcces());
        holder.tvGratuit.setText(String.format("Gratuit: %s", station.getGratuit()));

        // You can add any other fields here to display station information

        // Assuming the favorite icon is static for all favorite stations
        holder.favoriteButton.setImageResource(R.drawable.heart_filled_icon);  // Filled heart

        // Handle click on heart icon (optional)
        holder.favoriteButton.setOnClickListener(v -> {
            // You can remove the station from favorites if you want
            removeFromFavorites(station);
            favoriteStations.remove(position); // Remove from the list
            notifyItemRemoved(position); // Notify the adapter of the change
        });
    }

    @Override
    public int getItemCount() {
        return favoriteStations.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView tvMetaNameCom, tvMetaNameDep, tvConditionAcces, tvGratuit;
        ImageView favoriteButton;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            tvMetaNameCom = itemView.findViewById(R.id.tvMetaNameCom);
            tvMetaNameDep = itemView.findViewById(R.id.tvMetaNameDep);
            tvConditionAcces = itemView.findViewById(R.id.tvConditionAcces);
            tvGratuit = itemView.findViewById(R.id.tvGratuit);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }

    // Remove station from favorites (SharedPreferences)
    private void removeFromFavorites(Result station) {
        SharedPreferences preferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> favorites = preferences.getStringSet("favoriteStations", new HashSet<>());
        favorites.remove(station.getCoordonneesxy()); // Or use station.getId() if you are using IDs
        editor.putStringSet("favoriteStations", favorites);
        editor.apply();
    }
}

