package com.example.victorysquarepartners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.victorysquarepartners.entities.Country;
import com.example.victorysquarepartners.entities.IHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://universities.hipolabs.com/search?country/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView countryListView = findViewById(R.id.countryListView);

        readData(countryReceived -> {
            List<String> countriesForListView =  getCountryNames(countryReceived);
            ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, countriesForListView);
            countryListView.setAdapter(countryAdapter);

            countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedCountry = (String) countryListView.getAdapter().getItem(i);
                    Country country = getSelectedCountry(countryReceived, selectedCountry);
                    openActivity(country);
                }
            });

        });

    }

    private Country getSelectedCountry(List<Country> countryReceived, String selectedCountry) {
        Country result = new Country();

        for(Country country: countryReceived){
            if(selectedCountry.equalsIgnoreCase(country.getCountry())){
                result = country;
                break;
            }
        }
        return result;
    }

    private void openActivity(Country selectedCountry) {
        Intent intent = new Intent(this, CountryActivity.class);

        intent.putStringArrayListExtra("domains", (ArrayList<String>) selectedCountry.getDomains());
        intent.putExtra("alpha_two_code", selectedCountry.getAlphaTwoCode());
        intent.putExtra("country", selectedCountry.getCountry());
        intent.putStringArrayListExtra("web_pages", (ArrayList<String>) selectedCountry.getWebPages());
        intent.putExtra("name", selectedCountry.getName());
        intent.putExtra("state_province", selectedCountry.getStateProvince());

        startActivity(intent);
    }

    private List<String> getCountryNames(List<Country> countryReceived) {
        Set<String> setResult = new HashSet<>();

        for(Country country: countryReceived){
            setResult.add(country.getCountry());
        }

        List<String> result = new ArrayList<>(setResult);
        Collections.sort(result);

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