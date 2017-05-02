package com.think360.schoolshare.activity;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.think360.schoolshare.R;
import com.think360.schoolshare.fragment.StoreFragment.StoreFragment;
import com.think360.schoolshare.fragment.circular.CircularDetailFragment;
import com.think360.schoolshare.fragment.circular.CircularListFragment;
import com.think360.schoolshare.fragment.editprofile.EditProfileFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragmentSubmit;
import com.think360.schoolshare.fragment.main.MainFragment;
import com.think360.schoolshare.fragment.newsandevents.NewsAndEventFragment;
import com.think360.schoolshare.fragment.members.MembersFragment;
import com.think360.schoolshare.fragment.notification.NotificationDetailFragment;
import com.think360.schoolshare.fragment.notification.NotificationListFragment;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;


public class MainActivitySurinder extends AppCompatActivity implements OnFragmentInteractionListener, View.OnClickListener,CircularListFragment.OnFragmentInteractionListener,CircularDetailFragment.OnFragmentInteractionListener,NotificationListFragment.OnFragmentInteractionListener,NotificationDetailFragment.OnFragmentInteractionListener,StoreFragment.OnFragmentInteractionListener{


    public static MainActivitySurinder self;
    private boolean doubleBackToExitPressedOnce = false;
    private MainFragment mainFragment;
    public static LinearLayout bottomButtonMembers, bottomButtonStore, bottomButtonNewsAndEvents, bottomButtonMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        self = this;
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        setContentView(R.layout.activity_main_surinder);


        bottomButtonMembers = (LinearLayout) findViewById(R.id.bottomButtonMembers);
        bottomButtonStore = (LinearLayout) findViewById(R.id.bottomButtonStore);
        bottomButtonNewsAndEvents = (LinearLayout) findViewById(R.id.bottomButtonNewsAndEvents);
        bottomButtonMore = (LinearLayout) findViewById(R.id.bottomButtonMore);

        bottomButtonMembers.setOnClickListener(this);
        bottomButtonStore.setOnClickListener(this);
        bottomButtonNewsAndEvents.setOnClickListener(this);
        bottomButtonMore.setOnClickListener(this);


        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            initScreen();

        } else {
            // restoring the previously created fragment
            // and getting the reference
            mainFragment = (MainFragment) getSupportFragmentManager().getFragments().get(0);
        }
    }


    private void initScreen() {
        //  mainFragment = new MainFragment();

        //send intent value to mainFragment comming from DashBoardActivity
        mainFragment = MainFragment.newInstance(getIntent().getIntExtra("getClickedFragment", 100), "MainFragment");

        // Creating the ViewPager container fragment once
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        ((ImageView) bottomButtonMembers.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appGrey));
        ((TextView) bottomButtonMembers.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                .appGrey));

        ((ImageView) bottomButtonStore.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appGrey));
        ((TextView) bottomButtonStore.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                .appGrey));

        ((ImageView) bottomButtonNewsAndEvents.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appGrey));
        ((TextView) bottomButtonNewsAndEvents.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                .appGrey));

        ((ImageView) bottomButtonMore.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appGrey));
        ((TextView) bottomButtonMore.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                .appGrey));

        switch (v.getId()) {
            case R.id.bottomButtonMembers:
                MainFragment.callBack(0);
                animateOverShoot(bottomButtonMembers);

                ((ImageView) bottomButtonMembers.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appPeach));
                ((TextView) bottomButtonMembers.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                        .appPeach));

                break;
            case R.id.bottomButtonStore:
                MainFragment.callBack(1);
                animateOverShoot(bottomButtonStore);

                ((ImageView) bottomButtonStore.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appPeach));
                ((TextView) bottomButtonStore.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                        .appPeach));


                break;
            case R.id.bottomButtonNewsAndEvents:
                MainFragment.callBack(2);

                animateOverShoot(bottomButtonNewsAndEvents);
                ((ImageView) bottomButtonNewsAndEvents.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appPeach));
                ((TextView) bottomButtonNewsAndEvents.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                        .appPeach));

                break;
            case R.id.bottomButtonMore:

                MainFragment.callBack(3);
                animateOverShoot(bottomButtonMore);
                ((ImageView) bottomButtonMore.getChildAt(0)).setColorFilter(getResources().getColor(R.color.appPeach));
                ((TextView) bottomButtonMore.getChildAt(1)).setTextColor(ContextCompat.getColor(MainActivitySurinder.this, R.color
                        .appPeach));
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (!mainFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class

            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
                super.onBackPressed();
//                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;

            Toast.makeText(this, "Please BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        } else {
            // carousel handled the back pressed task
            // do not call super


        }
    }


   private void animateOverShoot(LinearLayout view) {
        view.getChildAt(0).setScaleX(0);
        view.getChildAt(0).setScaleY(0);
        view.getChildAt(0).animate()
                .scaleX(1)
                .scaleY(1)
                .alpha(1)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(150)
                .start();
    }
}
