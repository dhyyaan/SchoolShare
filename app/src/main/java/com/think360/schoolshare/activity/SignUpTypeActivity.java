package com.think360.schoolshare.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.think360.schoolshare.R;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.utils.SharePreferenceData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by think360 on 08/03/17.
 */

public class SignUpTypeActivity extends RuntimePermissionsActivity implements View.OnClickListener {

    private CallbackManager callbackManager;
    private AppCompatButton addManually;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(getLayoutResourceId());

        permissions();
        initView();
        listener();
        facebook();


    }

    private void permissions() {
        int REQUEST_PERMISSIONS = 20;
        SignUpTypeActivity.super.requestAppPermissions(new
                String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, R.string.app_name, REQUEST_PERMISSIONS);
    }

    private void initView() {

        addManually = (AppCompatButton) findViewById(R.id.btnResManually);

    }

    private void listener() {

        addManually.setOnClickListener(this);
        addManually.setOnClickListener(this);
    }

    private void facebook() {

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code


                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {


                                try {

                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.ISLOGIN, "true");
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.ID, object.getString("id"));
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.FIRST_NAME, object.getString("first_name"));
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.LAST_NAME, object.getString("last_name"));
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.GENDER, object.getString("gender").toLowerCase());
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.BIRTHDAY, object.getString("birthday"));
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.EMAIL, object.getString("email"));
                                    SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.MATERIAL_STATUS, object.getString("relationship_status"));
                                    // editor.putString("current_location",object.getJSONObject("location").getString("name").toLowerCase());

                                    if (object.has("picture")) {
                                        SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.PICTURE, object.getJSONObject("picture").getJSONObject("data").getString("url"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                LoginManager.getInstance().logOut();
                                Intent intentt = new Intent(SignUpTypeActivity.this, SignUpActivity.class);
                                startActivity(intentt);

                            }


                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,gender,birthday,email,relationship_status,picture,location,hometown,education,work");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_signup_type;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnResManually:

                SharePreferenceData.saveString(SignUpTypeActivity.this, ProfileConstent.ISLOGIN, "false");
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);

                break;
        }
    }
}
