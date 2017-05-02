package com.think360.schoolshare.fragment.editprofile;

/**
 * Created by think360user on 8/2/17.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.databinding.LayoutPersonalInfoBinding;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.model.ProfileModel;
import com.think360.schoolshare.surinder.utils.RootFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PersonalInfoEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoEditFragment extends RootFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    private static JSONObject objS;
    private static JSONObject objImage;
    private String mFName;
    private String mMName;
    private String mLNAME;
    private String mNNAME;
    private String mGENDER;
    private String mMSTATUS;
    private String mDOB;
    private String mADDITIONAL_INFO;
    private String mIMAGE_URL;

    private FrameLayout back;
    // TODO: Rename and change types of parameters

    //private String mParam2;
    private OnFragmentInteractionListener mListener;

    public PersonalInfoEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param obj    Parameter 1.
     * @param obj2 Parameter 2.
     * @return A new instance of fragment PersonalInfoEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfoEditFragment newInstance(JSONObject obj, JSONObject obj2) {
        PersonalInfoEditFragment fragment = new PersonalInfoEditFragment();
        objS = obj;
        objImage = obj2;
        Bundle args = new Bundle();
        try {


            //  args.putString(ProfileConstent.USE_PROFILE_AS, obj.getString(ProfileConstent.USE_PROFILE_AS));
            args.putString(ProfileConstent.FIRST_NAME, obj.getString(ProfileConstent.FIRST_NAME));
            args.putString(ProfileConstent.MIDDLE_NAME, obj.getString(ProfileConstent.MIDDLE_NAME));
            args.putString(ProfileConstent.LAST_NAME, obj.getString(ProfileConstent.LAST_NAME));
            args.putString(ProfileConstent.NICK_NAME, obj.getString(ProfileConstent.NICK_NAME));
            args.putString(ProfileConstent.GENDER, obj.getString(ProfileConstent.GENDER));
            // args.putString(ProfileConstent.ROLE_IN_SCHOOL, obj.getString(ProfileConstent.ROLE_IN_SCHOOL));
            args.putString(ProfileConstent.MATERIAL_STATUS, obj.getString(ProfileConstent.MATERIAL_STATUS));
            args.putString(ProfileConstent.DOB, obj.getString(ProfileConstent.DOB));
            args.putString(ProfileConstent.ADDTIONAL_INFO, obj.getString(ProfileConstent.ADDTIONAL_INFO));
            args.putString(ProfileConstent.IMAGE_URL, obj2.getString(ProfileConstent.IMAGE_URL));
            //args.putString(ProfileConstent.IMAGE_ID, obj.getString(ProfileConstent.IMAGE_ID));
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
            if (getArguments() != null) {
                this.mFName = getArguments().getString(ProfileConstent.FIRST_NAME);
                this.mMName = getArguments().getString(ProfileConstent.MIDDLE_NAME);
                this.mLNAME = getArguments().getString(ProfileConstent.LAST_NAME);
                this.mNNAME = getArguments().getString(ProfileConstent.NICK_NAME);
                this.mGENDER = getArguments().getString(ProfileConstent.GENDER);
                this.mMSTATUS = getArguments().getString(ProfileConstent.MATERIAL_STATUS);
                this.mDOB = getArguments().getString(ProfileConstent.DOB);
                this.mADDITIONAL_INFO = getArguments().getString(ProfileConstent.ADDTIONAL_INFO);
                this.mIMAGE_URL = getArguments().getString(ProfileConstent.IMAGE_URL);

                // mParam2 = getArguments().getString(ARG_PARAM2);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutPersonalInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_personal_info, container, false);
        ProfileModel profile = new ProfileModel(mFName, mMName, mLNAME, mNNAME, mGENDER, mMSTATUS, mDOB, mADDITIONAL_INFO,mIMAGE_URL);

        View view = binding.getRoot();
        binding.setProfile(profile);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout iv_back = (FrameLayout) view.findViewById(R.id.back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivitySurinder.self.onBackPressed();
            }
        });

        FrameLayout edit_profile = (FrameLayout) view.findViewById(R.id.edit);
        edit_profile.setOnClickListener(this);


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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
                // MainFragment.callBack(5);
                break;
            case R.id.edit:
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                // Store the Fragment in stack
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragContainer, PersonalInfoEditFragmentSubmit.newInstance(objS, objImage)).commitAllowingStateLoss();

                break;
        }
    }


}
