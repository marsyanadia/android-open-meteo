package com.example.weatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Callback;

public class Wadapter extends RecyclerView.Adapter {

    private final Context ctx;
    private List<DataWeather> weathers;

    public Wadapter(Context ctx, List<DataWeather> weathers) {
        this.ctx = (Context) ctx;
        this.weathers = weathers;
    }


    public static class VHcontact extends RecyclerView.ViewHolder {
        public TextView tvDate;
        public TextView tvCondition;
        public ImageView icon;

        public VHcontact(View itemView) {
            super(itemView);
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.tvCondition = itemView.findViewById(R.id.tvDCondition);
            this.icon = itemView.findViewById(R.id.tvIcon);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHcontact(LayoutInflater.from(this.ctx)
                .inflate(R.layout.rvweather, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataWeather dw = this.weathers.get(position);
        VHcontact vh = (VHcontact) holder;
        vh.tvDate.setText(dw.date);
        vh.tvCondition.setText(dw.getWeather());
        vh.icon.setImageResource(dw.getImg());
    }

    @Override
    public int getItemCount() {
        return this.weathers.size();
    }

}


