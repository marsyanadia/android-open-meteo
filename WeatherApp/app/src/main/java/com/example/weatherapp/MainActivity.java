package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvWeather;
    private Wadapter wadapter;
    private TextView tvLocation, tvCondition, tvTemperature, tvHasilT, tvWS,tvHasilWS, tvHasilKoor, tvLongitude, tvHasilKoor2, tvLatitude;
    private TextView tvdaily;
    private ImageView iconW;
    private List<DataWeather> weathers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvdaily = this.findViewById(R.id.tvDaily);

        this.iconW = this.findViewById(R.id.iconWeather);
        this.tvLocation = this.findViewById(R.id.tvLocation);
        this.tvCondition = this.findViewById(R.id.tvCondition);
        this.tvTemperature = this.findViewById(R.id.tvTemperature);
        this.tvHasilT = this.findViewById(R.id.tvHasilT);
        this.tvWS = this.findViewById(R.id.tvWS);
        this.tvHasilWS = this.findViewById(R.id.tvHasilWS);
        this.tvLatitude = this.findViewById(R.id.tvLatitude);
        this.tvHasilKoor = this.findViewById(R.id.tvHasilKoor1);
        this.tvLongitude = this.findViewById(R.id.tvLongitude);
        this.tvHasilKoor2 = this.findViewById(R.id.tvHasilKoor2);

        this.tvTemperature.setText("Temperature");
        this.tvWS.setText("Wind Speed");
        this.tvLatitude.setText("Latitude");
        this.tvLongitude.setText("Longitude");
        this.tvdaily.setText("Daily Weather");

        rvWeather = this.findViewById(R.id.rvWeather);

        Bundle WeatherVolley = getIntent().getExtras();
        int wc = WeatherVolley.getInt("code");
        String temperature = WeatherVolley.getString("temperature");
        String windSpeed = WeatherVolley.getString("windSpeed");
        String latitude = WeatherVolley.getString("latitude");
        String longitude = WeatherVolley.getString("longitude");
        weathers = (List<DataWeather>) WeatherVolley.getSerializable("dataWeather");


        Bundle WeatherRetrofit = getIntent().getExtras();
        int Rcode = WeatherRetrofit.getInt("code");
        String Rtemperature = WeatherVolley.getString("temperature");
        String RwindSpeed = WeatherVolley.getString("windSpeed");
        String Rlatitude = WeatherVolley.getString("latitude");
        String Rlongitude = WeatherVolley.getString("longitude");
        weathers = (List<DataWeather>) WeatherRetrofit.getSerializable("dataWeather");

        DataWeather dw = new DataWeather();

        if (WeatherVolley != null){
            dw.setCodeCondition(wc);
            iconW.setImageResource(dw.getImg());
            tvCondition.setText(dw.getWeather());
            tvHasilT.setText(temperature);
            tvHasilWS.setText(windSpeed);
            tvHasilKoor.setText(latitude);
            tvHasilKoor2.setText(longitude);
            if (latitude.equals("-8.0") && longitude.equals("112.625")){
                tvLocation.setText("Malang");
            } else
                tvLocation.setText("data not found");
        } else if (WeatherRetrofit != null){
            dw.setCodeCondition(Rcode);
            iconW.setImageResource(dw.getImg());
            tvCondition.setText(dw.getWeather());
            tvHasilT.setText(Rtemperature);
            tvHasilWS.setText(RwindSpeed);
            tvHasilKoor.setText(Rlatitude);
            tvHasilKoor2.setText(Rlongitude);
        }

        wadapter = new Wadapter(this, weathers);
        rvWeather.setLayoutManager(new LinearLayoutManager(this));
        rvWeather.setAdapter(wadapter);
    }



}