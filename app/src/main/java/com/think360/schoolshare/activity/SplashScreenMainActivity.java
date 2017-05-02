package com.think360.schoolshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import android.view.MotionEvent;
import android.view.View;

import android.view.WindowManager;
import android.widget.SeekBar;
import com.think360.schoolshare.R;



public class SplashScreenMainActivity extends BaseActivity {
    private Handler handle = new Handler();
    private SeekBar simpleSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayoutResourceId());
        initViews();
    }

    private void initViews() {
        // initiate  views


        simpleSeekBar = (SeekBar) findViewById(R.id.seekBar);
        simpleSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        final int totalProgressTime = 500;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(50);
                        jumpTime += 50;
                        simpleSeekBar.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Intent loginRegister = new Intent(SplashScreenMainActivity.this, LoginSignUpActivity.class);
                loginRegister.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(loginRegister);
                finish();
            }
        };
        t.start();
    }
    @Override
    protected void onStop() {        super.onStop();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        handle.removeCallbacksAndMessages(null);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        handle.removeCallbacksAndMessages(null);
    }



    @Override
    protected int getLayoutResourceId() {
        return R.layout.splash_screen_activity_main;
    }


}
