package com.think360.schoolshare.fragment.circular;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by think360 on 17/02/17.
 */

public interface GetCirculars {

    @GET("get-circular")
    Call<List<Example>> getCirculars();
}
