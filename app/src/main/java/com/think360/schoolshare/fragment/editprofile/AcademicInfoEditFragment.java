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
import com.think360.schoolshare.databinding.LayoutAcademicInfoBinding;


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
 * Use the {@link AcademicInfoEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcademicInfoEditFragment extends RootFragment implements View.OnClickListener{
    private static JSONObject objA;

    // TODO: Rename parameter arguments, choose names that match
    private String mAddmitionNo;
    private String mBatch;
    private String mYoj;
    private String mYol;
    private String mHouse;
    private String mChapter;


    private OnFragmentInteractionListener mListener;

    public AcademicInfoEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param obj Parameter 1.
     * @param obj2 Parameter 2.
     * @return A new instance of fragment NewsAndEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcademicInfoEditFragment newInstance(JSONObject obj, JSONObject obj2) {
        AcademicInfoEditFragment fragment = new AcademicInfoEditFragment();
        objA = obj;

        Bundle args = new Bundle();
        try {


            //  args.putString(ProfileConstent.USE_PROFILE_AS, obj.getString(ProfileConstent.USE_PROFILE_AS));
            args.putString(ProfileConstent.ADMISSION_NO, obj.getString(ProfileConstent.ADMISSION_NO));
            args.putString(ProfileConstent.BATCH_OF, obj.getString(ProfileConstent.BATCH_OF));
            args.putString(ProfileConstent.YEAR_OF_JOIN, obj.getString(ProfileConstent.YEAR_OF_JOIN));
            args.putString(ProfileConstent.YEAR_OF_LEAVE, obj.getString(ProfileConstent.YEAR_OF_LEAVE));
            args.putString(ProfileConstent.HOUSE, obj.getString(ProfileConstent.HOUSE));
            args.putString(ProfileConstent.CHAPTER_ID, obj.getString(ProfileConstent.CHAPTER_ID));


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
                this.mAddmitionNo = getArguments().getString(ProfileConstent.ADMISSION_NO);
                this.mBatch = getArguments().getString(ProfileConstent.BATCH_OF);
                this.mYoj = getArguments().getString(ProfileConstent.YEAR_OF_JOIN);
                this.mYol = getArguments().getString(ProfileConstent.YEAR_OF_LEAVE);
                this.mHouse = getArguments().getString(ProfileConstent.HOUSE);
                this.mChapter = getArguments().getString(ProfileConstent.CHAPTER_ID); //Need Chapter with ids


                // mParam2 = getArguments().getString(ARG_PARAM2);
            }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

           LayoutAcademicInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_academic_info, container, false);
           ProfileModel profile = new ProfileModel(mAddmitionNo, mBatch, mYoj, mYol, mHouse, mChapter);

           View view = binding.getRoot();
           binding.setProfile(profile);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout iv_back = (FrameLayout) view.findViewById(R.id.back);
        iv_back.setOnClickListener(this);

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
        switch(view.getId()){
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
                //MainFragment.callBack(5);
                break;
            case R.id.edit:
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                // Store the Fragment in stack
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragContainer,AcademicInfoEditFragmentSubmit.newInstance(objA, "")).commitAllowingStateLoss();
                //MainFragment.callBack(12);
                break;
        }
    }


}
