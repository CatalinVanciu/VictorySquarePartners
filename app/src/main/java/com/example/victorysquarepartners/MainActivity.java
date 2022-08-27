package com.example.victorysquarepartners;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.victorysquarepartners.entities.Country;
import com.example.victorysquarepartners.entities.IHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://universities.hipolabs.com/search?country/";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView countryListView = findViewById(R.id.countryListView);
        ProgressBar progressBar = findViewById(R.id.progressBar);



        readData(receivedCountries -> {
            List<String> countriesForListView =  getCountryNames(receivedCountries);
            ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, countriesForListView);
            countryListView.setAdapter(countryAdapter);
            progressBar.setVisibility(View.INVISIBLE);

            countryListView.setOnItemClickListener((adapterView, view, i, l) -> {
                String selectedCountry = (String) countryListView.getAdapter().getItem(i);
                List<Country> nameCountries = getSelectedCountries(receivedCountries, selectedCountry);
                openActivity(nameCountries);
            });

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openActivity(List<Country> receivedCountries) {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra("selectedCountries", (Serializable) receivedCountries);
        startActivity(intent);
    }

    private List<String> getCountryNames(List<Country> nameCountryReceived) {
        Set<String> setResult = new HashSet<>();

        for(Country country: nameCountryReceived){
            setResult.add(country.getCountry());
        }

        List<String> result = new ArrayList<>(setResult);
        Collections.sort(result);

        return result;
    }

    private List<Country> getSelectedCountries(List<Country> countryReceived, String selectedCountry){
        List<Country> result = new ArrayList<>();

        for(Country country: countryReceived){
            if(country.getCountry().equalsIgnoreCase(selectedCountry)){
                result.add(country);
            }
        }

        return result;

    }

    private void readData(IHelper helper){
        List<Country> countries = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);


                for (int i=0;i<jsonArray.length();i++){
                    Gson gson = new Gson();
                    Country country = gson.fromJson(jsonArray.get(i).toString(), Country.class);
                    countries.add(country);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            helper.countryReceived(countries);

        }, error -> Log.d("VolleyError", error.toString()));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}