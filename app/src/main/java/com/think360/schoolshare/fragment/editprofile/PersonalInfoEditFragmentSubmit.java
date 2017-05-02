package com.think360.schoolshare.fragment.editprofile;

/**
 * Created by think360user on 8/2/17.
 */

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.baseurl.BaseUrl;

import com.think360.schoolshare.databinding.PersonalInfoEditSubmitBinding;
import com.think360.schoolshare.fragment.dialog.DatePickerFragment;
import com.think360.schoolshare.handlers.MyHandlers;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;

import com.think360.schoolshare.interfaces.ServerCallback;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.model.ProfileModel;
import com.think360.schoolshare.surinder.utils.RootFragment;
import com.think360.schoolshare.utils.AppHelper;
import com.think360.schoolshare.utils.Utils;
import com.think360.schoolshare.utils.VolleyMultipartRequest;
import com.think360.schoolshare.utils.imagecompressor.Compressor;
import com.think360.schoolshare.utils.imagecompressor.FileUtil;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalInfoEditFragmentSubmit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoEditFragmentSubmit extends RootFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static PersonalInfoEditSubmitBinding binding;


    // TODO: Rename and change types of parameters
    private String mId;
    private String mUProfileAs;
    private String mFName;
    private String mMName;
    private String mLNAME;
    private String mNNAME;
    private String mGENDER;
    private String mRoleInSchool;
    private String mMSTATUS;
    private String mDOB;
    private String mADDITIONAL_INFO;
    private String mImageId;
    private String mImageUrl;

    private OnFragmentInteractionListener mListener;

    private static final int RESULT_OK = -1;
    private static int RESULT_LOAD_IMAGE = 1;

    // Store instance variables
    private String userProfile, maritalStatus;
   // private ArrayAdapter<String> profileAdapter, materialAdapter;
   // private ImageView imageUploaded;
    private EditText firstName, middleName, lastName, nickName, dob, additionalInfo;
    private RadioGroup radioGroupGender, radioButtoRoleInSchool;
    private String gender = "Male", roleInSchool = "Student";
    MyHandlers handlers;

    public PersonalInfoEditFragmentSubmit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param obj1 Parameter 1.
     * @param obj2 Parameter 2.
     * @return A new instance of fragment PersonalInfoEditFragmentSubmit.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfoEditFragmentSubmit newInstance(JSONObject obj1, JSONObject obj2) {
        PersonalInfoEditFragmentSubmit fragment = new PersonalInfoEditFragmentSubmit();
        Bundle args = new Bundle();

        try {

            args.putString(ProfileConstent.ID, obj1.getString(ProfileConstent.ID));
            args.putString(ProfileConstent.USE_PROFILE_AS, obj1.getString(ProfileConstent.USE_PROFILE_AS));
            args.putString(ProfileConstent.FIRST_NAME, obj1.getString(ProfileConstent.FIRST_NAME));
            args.putString(ProfileConstent.MIDDLE_NAME, obj1.getString(ProfileConstent.MIDDLE_NAME));
            args.putString(ProfileConstent.LAST_NAME, obj1.getString(ProfileConstent.LAST_NAME));
            args.putString(ProfileConstent.NICK_NAME, obj1.getString(ProfileConstent.NICK_NAME));
            args.putString(ProfileConstent.GENDER, obj1.getString(ProfileConstent.GENDER));
            args.putString(ProfileConstent.ROLE_IN_SCHOOL, obj1.getString(ProfileConstent.ROLE_IN_SCHOOL));
            args.putString(ProfileConstent.MATERIAL_STATUS, obj1.getString(ProfileConstent.MATERIAL_STATUS));
            args.putString(ProfileConstent.DOB, obj1.getString(ProfileConstent.DOB));
            args.putString(ProfileConstent.ADDTIONAL_INFO, obj1.getString(ProfileConstent.ADDTIONAL_INFO));
            args.putString(ProfileConstent.IMAGE_ID, obj2.getString(ProfileConstent.IMAGE_ID));
            args.putString(ProfileConstent.IMAGE_URL, obj2.getString(ProfileConstent.IMAGE_URL));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      if (getArguments() != null) {
          this.mId = getArguments().getString(ProfileConstent.ID);
          this.mUProfileAs = getArguments().getString(ProfileConstent.USE_PROFILE_AS);
          this.mFName = getArguments().getString(ProfileConstent.FIRST_NAME);
          this.mMName = getArguments().getString(ProfileConstent.MIDDLE_NAME);
          this.mLNAME = getArguments().getString(ProfileConstent.LAST_NAME);
          this.mNNAME = getArguments().getString(ProfileConstent.NICK_NAME);
          this.mGENDER = getArguments().getString(ProfileConstent.GENDER);
          this.mRoleInSchool = getArguments().getString(ProfileConstent.ROLE_IN_SCHOOL);
          this.mMSTATUS = getArguments().getString(ProfileConstent.MATERIAL_STATUS);
          this.mDOB = getArguments().getString(ProfileConstent.DOB);
          this.mADDITIONAL_INFO = getArguments().getString(ProfileConstent.ADDTIONAL_INFO);
          this.mImageId = getArguments().getString(ProfileConstent.IMAGE_ID);
          this.mImageUrl = getArguments().getString(ProfileConstent.IMAGE_URL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.personal_info_edit_submit, container, false);
         ProfileModel profile = new ProfileModel(mId,mUProfileAs,mFName, mMName,mLNAME, mNNAME, mDOB,mADDITIONAL_INFO,mImageId,mImageUrl);
         View view = binding.getRoot();
         binding.setProfile(profile);
         handlers = new MyHandlers();

         binding.setHandlers(handlers);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.spinnerUseProfileAs.setSelection(((ArrayAdapter<String>)  binding.spinnerUseProfileAs.getAdapter()).getPosition(mUProfileAs), true);
        binding.spinnerMaterialStatus.setSelection(((ArrayAdapter<String>) binding.spinnerMaterialStatus.getAdapter()).getPosition(mMSTATUS), true);
        if (mGENDER.toLowerCase().equals("Male".toLowerCase())) {

            binding.radioButtonMale.setChecked(true);

        } else if(mGENDER.toLowerCase().equals("Female".toLowerCase())) {

            binding.radioButtonFemale.setChecked(true);

        }

        if (mRoleInSchool.equals("2")) {

            binding.radioButtonStu.setChecked(true);

        } else if(mRoleInSchool.equals("3")){

            binding.radioButtonStaff.setChecked(true);
        }

      /*  binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivitySurinder.self.onBackPressed();
            }
        });*/
     /*   binding.spinnerUseProfileAs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pInfomap.put("profile_as",  binding.spinnerUseProfileAs.getSelectedItem().toString());

           }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/






     /*  binding.radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonMale:
                        // do operations specific to this selection


                        pInfomap.put("gender", binding.radioButtonMale.getText().toString());

                        break;
                    case R.id.radioButtonFemale:
                        // do operations specific to this selection

                        pInfomap.put("gender", binding.radioButtonFemale.getText().toString());

                        break;

                }
                //  Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });*/


      /* binding.radioButtoRoleInSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonStu:
                        // do operations specific to this selection
                       // RadioButton radioStu = (RadioButton) view.findViewById(checkedId);
                        pInfomap.put("role_in_school", "2");

                        break;
                    case R.id.radioButtonStaff:
                        // do operations specific to this selection
                      //  RadioButton radioStaff = (RadioButton) view.findViewById(checkedId);
                        pInfomap.put("role_in_school", "3");

                        break;

                }
                //  Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });*/



       /* binding.spinnerMaterialStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pInfomap.put("marital_status", binding.spinnerMaterialStatus.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */

        binding.imageUploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });




    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //GETTING IMAGE FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data)
                handlers.onActivityResult(requestCode, resultCode,data);

    }



    @Override
    public void onResume() {
        super.onResume();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



    }


}
