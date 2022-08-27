package com.example.victorysquarepartners.utils;

import android.widget.Spinner;

import com.example.victorysquarepartners.entities.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Operations {

    public List<String> getCountryNames(List<Country> nameCountryReceived) {
        Set<String> setResult = new HashSet<>();

        for(Country country: nameCountryReceived){
            setResult.add(country.getCountry());
        }

        List<String> result = new ArrayList<>(setResult);
        Collections.sort(result);

        return result;
    }

    public List<Country> getSelectedCountries(List<Country> countryReceived, String selectedCountry){
        List<Country> result = new ArrayList<>();

        for(Country country: countryReceived){
            if(country.getCountry().equalsIgnoreCase(selectedCountry)){
                result.add(country);
            }
        }

        return result;

    }

    public ArrayList<String> getNames(List<Country> selectedCountries){
        ArrayList<String> result = new ArrayList<>();
        for(Country country : selectedCountries){
            result.add(country.getName());
        }
        return result;
    }

    public Country assignValueToSearcher(List<Country> selectedCountries, Spinner nameSpinner){
        
        Country searcher = new Country();
        
        for(Country country : selectedCountries){
            if(country.getName().equalsIgnoreCase(nameSpinner.getSelectedItem().toString())){
                searcher = country;
                break;
            }
        }
        return searcher;
    }

}
