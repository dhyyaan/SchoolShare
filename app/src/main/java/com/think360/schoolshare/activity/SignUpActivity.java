package com.think360.schoolshare.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.think360.schoolshare.R;
import com.think360.schoolshare.adapter.RegisterPagerAdapter;
import com.think360.schoolshare.surinder.utils.NonSwipeableViewPager;
import com.think360.schoolshare.utils.SharePreferenceData;

import org.json.JSONObject;


public class SignUpActivity extends RuntimePermissionsActivity implements View.OnClickListener{

    public  RegisterPagerAdapter adapterViewPager;
    public NonSwipeableViewPager vpPager;
    private ImageView imageViewStep1,imageViewStep2,imageViewStep3,imageViewStep4;
    private View line1,line2,line3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());
        Log.d("Facebook",SharePreferenceData.getString(this,"isLogin","false"));
        permissions();
        initViews();
        listeners();


    }

    private void listeners() {

        imageViewStep1.setOnClickListener(this);
        imageViewStep2.setOnClickListener(this);
        imageViewStep3.setOnClickListener(this);

    }

    private void permissions() {
        int REQUEST_PERMISSIONS = 20;
        SignUpActivity.super.requestAppPermissions(new
                String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, R.string.app_name, REQUEST_PERMISSIONS);
    }

    private void initViews() {
        vpPager = (NonSwipeableViewPager) findViewById(R.id.vpPagerRegister);
        adapterViewPager = new RegisterPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOffscreenPageLimit(4);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3= findViewById(R.id.line3);
        imageViewStep1 = (ImageView)findViewById(R.id.imageViewStep1);
        imageViewStep2 = (ImageView)findViewById(R.id.imageViewStep2);
        imageViewStep3= (ImageView)findViewById(R.id.imageViewStep3);
        imageViewStep4= (ImageView)findViewById(R.id.imageViewStep4);


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sign_up;
    }
    @Override
    public void onPermissionsGranted(int requestCode) {
        // Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
         switch(view.getId()){
             case R.id.imageViewStep1:
                   if(vpPager.getCurrentItem()==1 || vpPager.getCurrentItem()==2 || vpPager.getCurrentItem()==3 ) {
                       line1.setBackgroundColor(Color.WHITE);
                       line2.setBackgroundColor(Color.WHITE);
                       line3.setBackgroundColor(Color.WHITE);
                       imageViewStep2.setImageResource(R.mipmap.step_2);
                       imageViewStep3.setImageResource(R.mipmap.step_3);
                       imageViewStep4.setImageResource(R.mipmap.step_4);
                       vpPager.setCurrentItem(0);
                    }
             break;
             case R.id.imageViewStep2:
                 if(vpPager.getCurrentItem()==2 || vpPager.getCurrentItem()==3 ) {
                     line2.setBackgroundColor(Color.WHITE);
                     line3.setBackgroundColor(Color.WHITE);
                     imageViewStep3.setImageResource(R.mipmap.step_3);
                     imageViewStep4.setImageResource(R.mipmap.step_4);
                     vpPager.setCurrentItem(1);
                 }
             break;
             case R.id.imageViewStep3:
                 if(vpPager.getCurrentItem()==3) {

                     line3.setBackgroundColor(Color.WHITE);
                     imageViewStep4.setImageResource(R.mipmap.step_4);
                     vpPager.setCurrentItem(2);
                 }
             break;
         }
    }

    @Override
    public void onResume() {
        super.onResume();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // onResumeFragment();


    }
    public void setCurrentPosition(int position) {
        // when notify then set manually current position.
        vpPager.setCurrentItem(position);

    }




    // printHashKey();


      /*  AppEventsLogger.activateApp(this);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
              //  AccessToken accessToken = loginResult.getAccessToken();

                Profile profile = Profile.getCurrentProfile();
                displayMessage(profile);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });*/

}
