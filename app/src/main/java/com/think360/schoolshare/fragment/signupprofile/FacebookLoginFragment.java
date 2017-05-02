package com.think360.schoolshare.fragment.signupprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.SignUpActivity;
import com.think360.schoolshare.utils.SharePreferenceData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by think360user on 10/1/17.
 */
public class FacebookLoginFragment extends Fragment implements View.OnClickListener{

  //  RegisterPagerAdapter hh;
  //  private ImageView imageViewStep1;
  //  private CallbackManager callbackManager;
    private AppCompatButton addManually;
 //   private LoginButton loginButton;
    // newInstance constructor for creating fragment with arguments
    public static FacebookLoginFragment newInstance() {

        return new FacebookLoginFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


     //   FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

     //   callbackManager = CallbackManager.Factory.create();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.facebook_login_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  imageViewStep1  = (ImageView)getActivity().findViewById(R.id.imageViewStep1);

       //    loginButton = (LoginButton) view.findViewById(R.id.login_button);
       //  loginButton.setOnClickListener(this);


     //   addManually = (AppCompatButton) view.findViewById(R.id.btnResManually);


     //   addManually.setOnClickListener(this);
    }

   /*   @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     //   callbackManager.onActivityResult(requestCode, resultCode, data);

    }

  private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {


            // App code
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
Log.d("facebook ",object+"");


                           try {

                                SharePreferenceData.saveString(getActivity(),"isLogin","true");
                                SharePreferenceData.saveString(getActivity(),"id",object.getString("id"));
                                SharePreferenceData.saveString(getActivity(),"first_name",object.getString("first_name"));
                                SharePreferenceData.saveString(getActivity(),"last_name",object.getString("last_name"));
                                SharePreferenceData.saveString(getActivity(),"gender",object.getString("gender").toLowerCase());
                                SharePreferenceData.saveString(getActivity(),"birthday",object.getString("birthday"));
                                SharePreferenceData.saveString(getActivity(),"email",object.getString("email"));
                                SharePreferenceData.saveString(getActivity(),"relationship_status",object.getString("relationship_status").toLowerCase());
                                // editor.putString("current_location",object.getJSONObject("location").getString("name").toLowerCase());
                                if (object.has("picture")) {
                                        SharePreferenceData.saveString(getActivity(),"picture",object.getJSONObject("picture").getJSONObject("data").getString("url"));


                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                imageViewStep1.setImageResource(R.mipmap.step_1_2);
                                ((SignUpActivity) getContext()). setCurrentPosition(1);
                                 LoginManager.getInstance().logOut();
                            }


                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,first_name,last_name,gender,birthday,email,relationship_status,picture,location,hometown,education,work");
            request.setParameters(parameters);
            request.executeAsync();



        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };
   */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
             //   loginButton.setFragment(this);
                // for multiple request
             //   loginButton.setReadPermissions(Arrays.asList("public_profile","user_about_me", "email", "user_birthday","user_education_history","user_hometown","user_relationships", "user_location","user_work_history"));
                //for single request
                // loginButton.setReadPermissions("public_profile");
             //   loginButton.registerCallback(callbackManager, callback);


                break;
            case R.id.btnResManually:
              //  SharePreferenceData.saveString(getActivity(),"isLogin","false");
              //  imageViewStep1.setImageResource(R.mipmap.step_1_2);
                ((SignUpActivity) getContext()).setCurrentPosition(1);


                break;
        }
    }
}