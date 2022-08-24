package com.example.victorysquarepartners.entities;

import java.util.List;

public class Country {
    private List<String> domains;
    private String alphaTwoCode;
    private String country;
    private List<String> webPages;
    private String name;
    private String stateProvince;

    public Country(){

    }

    public Country(List<String> domains, String alphaTwoCode, String country, List<String> webPages, String name, String stateProvince){
        this.domains = domains;
        this.alphaTwoCode = alphaTwoCode;
        this.country = country;
        this.webPages = webPages;
        this.name = name;
        if(stateProvince.isEmpty()){
            this.stateProvince = "null";
        } else{
            this.stateProvince = stateProvince;
        }
    }

    public List<String> getDomains() {
        return domains;
    }

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getWebPages() {
        return webPages;
    }

    public String getName() {
        return name;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    @Override
    public String toString() {
        return "Country{" +
                "domains=" + domains +
                ", alphaTwoCode='" + alphaTwoCode + '\'' +
                ", country='" + country + '\'' +
                ", webPages=" + webPages +
                ", name='" + name + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                '}';
    }
}


