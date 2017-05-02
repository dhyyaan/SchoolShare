package com.think360.schoolshare.fragment.editprofile;

/**
 * Created by think360user on 8/2/17.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;

import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;


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
public class ProfessionalInfoEditFragmentSubmit extends RootFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    private String mOrganisationName;
    private String mIndustry;
    private String mDesignation;
    private String mLinkdin;
    private String mResumeId;
    private String mResume;
    private String mADDITIONAL_INFOP;

   private OnFragmentInteractionListener mListener;

    public ProfessionalInfoEditFragmentSubmit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param obj Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsAndEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfessionalInfoEditFragmentSubmit newInstance(JSONObject obj, String param2) {
        ProfessionalInfoEditFragmentSubmit fragment = new ProfessionalInfoEditFragmentSubmit();
        Bundle args = new Bundle();

        try {



            args.putString(ProfileConstent.ORGANIZATION_NAME, obj.getString(ProfileConstent.ORGANIZATION_NAME));
            args.putString(ProfileConstent.INDUSTRY, obj.getString(ProfileConstent.INDUSTRY));
            args.putString(ProfileConstent.DESIGNATION, obj.getString(ProfileConstent.DESIGNATION));
            args.putString(ProfileConstent.LINKDIN_LINK, obj.getString(ProfileConstent.LINKDIN_LINK));
            args.putString(ProfileConstent.RESUME_ID, obj.getString(ProfileConstent.RESUME_ID));
            args.putString(ProfileConstent.RESUME, obj.getString(ProfileConstent.RESUME));
            args.putString(ProfileConstent.ADDIONAL_INFOP, obj.getString(ProfileConstent.ADDIONAL_INFOP));

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
                this.mOrganisationName = getArguments().getString(ProfileConstent.ORGANIZATION_NAME);
                this.mIndustry = getArguments().getString(ProfileConstent.INDUSTRY);
                this.mDesignation = getArguments().getString(ProfileConstent.DESIGNATION);
                this.mLinkdin = getArguments().getString(ProfileConstent.LINKDIN_LINK);
                this.mResumeId = getArguments().getString(ProfileConstent.RESUME_ID);
                this.mResume = getArguments().getString(ProfileConstent.RESUME);
                this.mADDITIONAL_INFOP = getArguments().getString(ProfileConstent.ADDIONAL_INFOP);



            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment  ProfessionalInfoEditSubmit
      //  ProfessionalInfoEditSubmitBinding binding = DataBindingUtil.inflate(inflater, R.layout.professional_info_edit_submit, container, false); 
      //  ProfileModel profile = new ProfileModel(mOrganisationName, mIndustry, mDesignation, mLinkdin, mResumeId, mResume,mADDITIONAL_INFOP); 
        // Log.d("dhgs ",""+mOrganisationName+" "+mIndustry+" "+mDesignation+" "+mLinkdin+" "+mResumeId+" "+mResume+" "+mADDITIONAL_INFOP); 
       //  View view = binding.getRoot(); 


         return  DataBindingUtil.inflate(inflater, R.layout.professional_info_edit_submit, container, false).getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout iv_back = (FrameLayout) view.findViewById(R.id.back);
          iv_back.setOnClickListener(this);



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

                break;

        }
    }

}
