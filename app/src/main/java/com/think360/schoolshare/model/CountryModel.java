package com.think360.schoolshare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by think360 on 03/03/17.
 */

public class CountryModel {

    @SerializedName("country_id")
    private int country_id;

    @SerializedName("country_code")
    private String country_code;

    @SerializedName("country")
    private String country;

    public CountryModel(int country_id, String country_code, String country) {
        this.country_id = country_id;
        this.country_code = country_code;
        this.country = country;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
