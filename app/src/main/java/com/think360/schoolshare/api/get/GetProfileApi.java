package com.think360.schoolshare.api.get;

import android.util.Log;

import com.google.gson.JsonArray;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.interfaces.GsonArrayCallBack;
import com.think360.schoolshare.interfaces.UserProfileInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by think360 on 17/02/17.
 */

public class GetProfileApi {


//this api using for retrofit
   // private static Retrofit retrofit = null;


public static void getProfile(String id, final GsonArrayCallBack callback) {


    Call<JsonArray> call = new Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UserProfileInterface.class).getUserProfile(id);

    call.enqueue(new Callback<JsonArray>() {


        @Override
        public void onResponse(Call<JsonArray> call, retrofit2.Response<JsonArray> response) {
            //List<ProfileConstent> movies = response.getBody().getResults();

            //Toast.makeText(getActivity(), ""+response.body().toString(), Toast.LENGTH_SHORT).show();

           // Log.d("TAG", "ID: " + response.body());
            // responce =  response.body();
            callback.onSuccess(response.body());
        }

        @Override
        public void onFailure(Call<JsonArray> call, Throwable t) {
            // Log error here since request failed
            Log.e("TAG", t.toString());
        }
    });
}


}
