package com.example.victorysquarepartners.entities;

import java.util.List;

public class Country {
    private List<String> domains;
    private String alpha_two_code;
    private String country;
    private List<String> web_pages;
    private String name;
    private String state_province;

    public Country(){

    }

    public Country(List<String> domains, String alpha_two_code, String country, List<String> web_pages, String name, String state_province){
        this.domains = domains;
        this.alpha_two_code = alpha_two_code;
        this.country = country;
        this.web_pages = web_pages;
        this.name = name;
        this.state_province = state_province;
    }

    public List<String> getDomains() {
        return domains;
    }

    public String getAlphaTwoCode() {
        return alpha_two_code;
    }

    public String getCountry() {
        return country;
    }

    public List<String> getWebPages() {
        return web_pages;
    }

    public String getName() {
        return name;
    }

    public String getStateProvince() {
        return state_province;
    }

}


