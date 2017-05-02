package com.think360.schoolshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.think360.schoolshare.R;

public class LoginSignUpActivity extends BaseActivity implements View.OnClickListener{
    private AppCompatButton btnSignUp,btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        initViews();
        listener();
    }

    private void listener() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private void initViews() {
        btnSignIn = (AppCompatButton)findViewById(R.id.btnLogin);
        btnSignUp = (AppCompatButton)findViewById(R.id.btnSignUp);
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login_signup;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                Intent intent_dash = new Intent(getApplicationContext(),DashboardActivity.class);
                startActivity(intent_dash);
           //     finish();
                break;
            case R.id.btnSignUp:
              Intent intent = new Intent(getApplicationContext(),SignUpTypeActivity.class);

                startActivity(intent);
            //    finish();
                break;

        }

        
    }
}
