package com.think360.schoolshare.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by think360 on 03/03/17.
 */

public class StateCityModel {
    @SerializedName("id")
    private  int id;

    @SerializedName("country_state_id")
    private int country_state_id;

    @SerializedName("state_city")
    private String state_city;

    public StateCityModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountry_state_id() {
        return country_state_id;
    }

    public void setCountry_state_id(int country_state_id) {
        this.country_state_id = country_state_id;
    }

    public String getState_city() {
        return state_city;
    }

    public void setState_city(String state_city) {
        this.state_city = state_city;
    }
}
