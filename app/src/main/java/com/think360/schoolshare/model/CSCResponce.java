package com.think360.schoolshare.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by think360 on 04/03/17.
 */

public class CSCResponce {

    @SerializedName("results")
    private List<CountryModel> results;

    public List<CountryModel> getResults() {
        return results;
    }

    public void setResults(List<CountryModel> results) {
        this.results = results;
    }
}
