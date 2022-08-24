package com.example.victorysquarepartners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://universities.hipolabs.com/search?country/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveData();

    }

    private void retrieveData(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("test", jsonArray.get(i).toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }, error -> Log.d("VolleyError", error.toString()));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}