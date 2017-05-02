package com.think360.schoolshare.fragment.editprofile;

/**
 * Created by think360user on 7/2/17.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;

import com.think360.schoolshare.api.get.GetProfile;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.interfaces.ServerCallBackArray;
import com.think360.schoolshare.interfaces.ServerCallBackObj;
import com.think360.schoolshare.surinder.utils.RootFragment;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends RootFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static JSONObject RESPONCE1 =null;
    private static JSONObject RESPONCE2 = null;
    private static JSONObject RESPONCE3 = null;
    private static JSONObject RESPONCE4 = null;
    private OnFragmentInteractionListener mListener;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetProfile(getActivity(), "5", new ServerCallBackObj() {
            @Override
            public void onSuccess1(JSONObject responce) {
                RESPONCE1 = responce;
            }

            @Override
            public void onSuccess2(JSONObject responce) {
                RESPONCE2 = responce;

            }

            @Override
            public void onSuccess3(JSONObject responce) {
                RESPONCE3 = responce;

            }
            @Override
            public void onSuccess4(JSONObject responce) {
                RESPONCE4 = responce;

            }

        }).addQueue();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.layout_profile_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout rlPersonalInfo = (RelativeLayout)view.findViewById(R.id.rlPersonalInfo);
        rlPersonalInfo.setOnClickListener(this);

        RelativeLayout rlContactInfo = (RelativeLayout)view.findViewById(R.id.rlContactInfo);
        rlContactInfo.setOnClickListener(this);

        RelativeLayout rlAcademicInfo = (RelativeLayout)view.findViewById(R.id.rlAcademicInfo);
        rlAcademicInfo.setOnClickListener(this);

        RelativeLayout rlProfessionalInfo = (RelativeLayout)view.findViewById(R.id.rlProfessionalInfo);
        rlProfessionalInfo.setOnClickListener(this);

        FrameLayout back = (FrameLayout) view.findViewById(R.id.back);
        back.setOnClickListener(this);




    }




    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        switch(view.getId()){

            case R.id.rlPersonalInfo:

// Store the Fragment in stack
                if(RESPONCE1 != null && RESPONCE4!=null) {

                    transaction.replace(R.id.fragContainer, PersonalInfoEditFragment.newInstance(RESPONCE1, RESPONCE4)).commitAllowingStateLoss();
                    // MainFragment.callBack(6);
                }else{
                    Toast.makeText(getActivity(),getResources().getString(R.string.no_data),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rlContactInfo:

// Store the Fragment in stack
                if(RESPONCE2 != null && RESPONCE3 != null) {
                    transaction.replace(R.id.fragContainer, ContactInfoEditFragment.newInstance(RESPONCE2, RESPONCE3)).commitAllowingStateLoss();
                    //MainFragment.callBack(7);
                }else{
                    Toast.makeText(getActivity(),getResources().getString(R.string.no_data),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rlAcademicInfo:

// Store the Fragment in stack
                if(RESPONCE1 != null ) {
                    transaction.replace(R.id.fragContainer, AcademicInfoEditFragment.newInstance(RESPONCE1, RESPONCE1)).commitAllowingStateLoss();
                    //MainFragment.callBack(8);
                }else{
                    Toast.makeText(getActivity(),getResources().getString(R.string.no_data),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rlProfessionalInfo:

// Store the Fragment in stack
                if(RESPONCE1 != null ) {
                    transaction.replace(R.id.fragContainer, ProfessionalInfoEditFragment.newInstance(RESPONCE1, "")).commitAllowingStateLoss();
                    //MainFragment.callBack(9);
                }else{
                    Toast.makeText(getActivity(),getResources().getString(R.string.no_data),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
                //MainFragment.callBack(3);
                break;
        }
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


}
