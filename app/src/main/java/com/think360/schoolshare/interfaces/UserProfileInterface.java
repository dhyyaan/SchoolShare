package com.think360.schoolshare.interfaces;

import com.google.gson.JsonArray;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.model.ProfileConstent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by think360 on 17/02/17.
 */

public interface UserProfileInterface {
    @GET(BaseUrl.get_member)
    Call<JsonArray> getUserProfile(@Query(BaseUrl.chapter_id) String id);

}
