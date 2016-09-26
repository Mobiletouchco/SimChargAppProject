package com.mobiletouchco.model;

import java.util.ArrayList;

/**
 * Created by Prosanto on 9/2/16.
 */
public class CountryList {
    private String country_id="";
    private String country_name="";
    private String country_code="";
    private String mobile_country_code="";
    private String network_code="";


    public String getMobile_country_code() {
        return mobile_country_code;
    }

    public void setMobile_country_code(String mobile_country_code) {
        this.mobile_country_code = mobile_country_code;
    }

    public ArrayList<Operator>allOperator = new ArrayList<>();

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getNetwork_code() {
        return network_code;
    }

    public void setNetwork_code(String network_code) {
        this.network_code = network_code;
    }

    public ArrayList<Operator> getAllOperator() {
        return allOperator;
    }

    public void setAllOperator(ArrayList<Operator> allOperator) {
        this.allOperator = allOperator;
    }
}
