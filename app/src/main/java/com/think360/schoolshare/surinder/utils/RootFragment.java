package com.think360.schoolshare.surinder.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.think360.schoolshare.baseurl.BaseUrl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by shahabuddin on 6/6/14.
 */
public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }


    protected void transactFragment(int id, Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // Store the Fragment in stack
        transaction.addToBackStack(null);
        // transaction.replace(R.id.fragContainer, EventsFragment.newInstance("" + "", "")).commitAllowingStateLoss();
        transaction.replace(id, fragment).commitAllowingStateLoss();
    }

    @NonNull
    private Gson getGsonObject() {
        return new GsonBuilder().setLenient().create();

    }

    protected Retrofit getRetroObject() {
        return new Retrofit.Builder().baseUrl(BaseUrl.BASE_URL2).addConverterFactory(GsonConverterFactory.create(getGsonObject())).build();
    }
}
