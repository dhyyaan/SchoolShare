package com.think360.schoolshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.think360.schoolshare.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        setContentView(R.layout.activity_dashboard);
    }


    public void members(View view) {

        Intent intent = new Intent(DashboardActivity.this, MainActivitySurinder.class);
        switch (view.getId()) {
            //member fragment in main fragment inside callBack(int)
            case R.id.ll_members:
               // intent.putExtra("getClickedFragment", 0);

                break;

            //news_and_events fragment in main fragment inside callBack(int)
            case R.id.ll_news_and_events:
               // intent.putExtra("getClickedFragment", 2);

                break;

            //donate fragment in main fragment inside callBack(int)
            case R.id.ll_donate:
                intent.putExtra("getClickedFragment", 2);

                break;

            //store fragment in main fragment inside callBack(int)
            case R.id.ll_store:
                intent.putExtra("getClickedFragment", 3);

                break;

            //notifications fragment in main fragment inside callBack(int)
            case R.id.ll_notifications:
                intent.putExtra("getClickedFragment", 4);

                break;

            //circular fragment in main fragment inside callBack(int)
            case R.id.ll_circular:
                intent.putExtra("getClickedFragment", 5);

                break;

            default:

                break;

        }
        startActivity(intent);

    }

    /*public void circulars(View view) {

        startActivity(new Intent(DashboardActivity.this, MainActivitySurinder.class));
    }*/
}
