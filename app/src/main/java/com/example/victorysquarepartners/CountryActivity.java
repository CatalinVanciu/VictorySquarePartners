package com.example.victorysquarepartners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.victorysquarepartners.entities.Country;
import com.example.victorysquarepartners.utils.Operations;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    private List<Country> selectedCountries;
    private Spinner nameSpinner;
    private Country searcher;
    private final Operations operations = new Operations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Intent intent = getIntent();
        selectedCountries = (List<Country>) intent.getSerializableExtra("selectedCountries");

        init();

        nameSpinner = findViewById(R.id.nameSpinner);
        populateSpinner(nameSpinner, operations.getNames(selectedCountries));
        Spinner domainsSpinner = findViewById(R.id.domainsSpinner);
        Spinner webPagesSpinner = findViewById(R.id.webPagesSpinner);
        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searcher = operations.assignValueToSearcher(selectedCountries, nameSpinner);

                TextView stateProvinceTextView = findViewById(R.id.stateProvinceTextView);
                stateProvinceTextView.setText(searcher.getStateProvince());

                populateSpinner(domainsSpinner, (ArrayList<String>) searcher.getDomains());
                populateSpinner(webPagesSpinner, (ArrayList<String>) searcher.getWebPages());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void init() {
        TextView alphaTwoCodeTextView = findViewById(R.id.alphaTwoCodeTextView);
        alphaTwoCodeTextView.setText(selectedCountries.get(0).getAlphaTwoCode());
        TextView countryTextView = findViewById(R.id.countryTextView);
        countryTextView.setText(selectedCountries.get(0).getCountry());
    }

    private void populateSpinner(Spinner spinner, ArrayList<String> array){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list, array);
        adapter.setDropDownViewResource(R.layout.spinner_list);
        spinner.setAdapter(adapter);
    }

}