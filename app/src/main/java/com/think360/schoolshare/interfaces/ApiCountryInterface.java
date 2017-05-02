package com.think360.schoolshare.interfaces;

import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.model.CSCResponce;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by think360 on 03/03/17.
 */

public interface ApiCountryInterface {
    @GET(BaseUrl.get_country)
    Call<CSCResponce> getCountry();

}
