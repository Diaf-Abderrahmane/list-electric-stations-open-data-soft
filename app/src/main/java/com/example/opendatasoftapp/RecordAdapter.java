package com.example.opendatasoftapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DetailActivity with the selected Result object
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("result", record); // Pass the Result object
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return records != null ? records.size() : 0;
    }

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        TextView tvMetaNameCom, tvMetaNameDep, tvConditionAcces;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMetaNameCom = itemView.findViewById(R.id.tvMetaNameCom);
            tvMetaNameDep = itemView.findViewById(R.id.tvMetaNameDep);
            tvConditionAcces = itemView.findViewById(R.id.tvConditionAcces);
        }
    }

    public void updateRecords(List<Result> newRecords) {
        records = newRecords;
        notifyDataSetChanged();
    }
}

