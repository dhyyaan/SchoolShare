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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.databinding.LayoutContactInfoBinding;
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
 * Use the {@link ContactInfoEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfoEditFragment extends RootFragment implements View.OnClickListener{
    private static JSONObject objC = null;
    private static JSONObject objP = null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String ccurrentAdd;
    private String cCountry;
    private String cState;
    private String cCity;
    private String cPostalCode;
    private String cMobile;
    private String cPhone;
    private String cEmail;

    private String permanentAdd;
    private String pCountry;
    private String pState;
    private String pCity;
    private String pPostalCode;
    private String pMobile;
    private String pPhone;
    private String pEmail;


    private OnFragmentInteractionListener mListener;

    public ContactInfoEditFragment() {
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
    public static ContactInfoEditFragment newInstance(JSONObject obj, JSONObject obj2) {
        ContactInfoEditFragment fragment = new ContactInfoEditFragment();
        objC = obj;
        objP = obj2;
        Bundle args = new Bundle();
     //   Bundle argsp = new Bundle();
        try {

//Log.d("dddd",obj+"      "+obj2);
          //Getting current Address
            args.putString(ProfileConstent.ADDRESS, obj.getString(ProfileConstent.ADDRESS));
            args.putString(ProfileConstent.COUNTRY, obj.getString(ProfileConstent.COUNTRY));
            args.putString(ProfileConstent.STATE, obj.getString(ProfileConstent.STATE));
            args.putString(ProfileConstent.CITY, obj.getString(ProfileConstent.CITY));
            args.putString(ProfileConstent.POSTAL_CODE, obj.getString(ProfileConstent.POSTAL_CODE));
            args.putString(ProfileConstent.MOBILE, obj.getString(ProfileConstent.PHONE_NO));//mobile not coming from server
            // args.putString(ProfileConstent.ROLE_IN_SCHOOL, obj.getString(ProfileConstent.ROLE_IN_SCHOOL));
            args.putString(ProfileConstent.PHONE_NO, obj.getString(ProfileConstent.PHONE_NO));
            args.putString(ProfileConstent.EMAIL, obj.getString(ProfileConstent.EMAIL));


            //Getting permanent Address
         //   argsp.putString(ProfileConstent.ADDRESS, obj2.getString(ProfileConstent.ADDRESS));
         //  argsp.putString(ProfileConstent.COUNTRY, obj2.getString(ProfileConstent.COUNTRY));
          //  argsp.putString(ProfileConstent.STATE, obj2.getString(ProfileConstent.STATE));
           // argsp.putString(ProfileConstent.POSTAL_CODE, obj2.getString(ProfileConstent.POSTAL_CODE));
           // argsp.putString(ProfileConstent.MOBILE, obj2.getString(ProfileConstent.MOBILE));
            //   argsp.putString(ProfileConstent.PHONE_NO, obj2.getString(ProfileConstent.PHONE_NO));

            fragment.setArguments(args);
           // fragment.setArguments(argsp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

                this.ccurrentAdd = getArguments().getString(ProfileConstent.ADDRESS);
                this.cCountry = getArguments().getString(ProfileConstent.COUNTRY);
                this.cState = getArguments().getString(ProfileConstent.STATE);
                this.cCity = getArguments().getString(ProfileConstent.CITY);
                this.cPostalCode = getArguments().getString(ProfileConstent.POSTAL_CODE);
                this.cMobile = getArguments().getString(ProfileConstent.MOBILE);
                this.cPhone = getArguments().getString(ProfileConstent.PHONE_NO);
                this.cEmail= getArguments().getString(ProfileConstent.EMAIL);


               // this.permanentAdd = getArguments().getString(ProfileConstent.ADDRESS);
               // this.pCountry = getArguments().getString(ProfileConstent.COUNTRY);
              //  this.pState = getArguments().getString(ProfileConstent.STATE);
              //  this.pPostalCode = getArguments().getString(ProfileConstent.POSTAL_CODE);
               // this.pMobile = getArguments().getString(ProfileConstent.MOBILE);
               // this.pPhone = getArguments().getString(ProfileConstent.PHONE_NO);



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        LayoutContactInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_contact_info, container, false);
        ProfileModel profile = new ProfileModel(ccurrentAdd, cCountry,cState,cCity, cPostalCode, cMobile, cPhone, cEmail);
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
                break;
            case R.id.edit:
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                // Store the Fragment in stack
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragContainer,ContactInfoEditFragmentSubmit.newInstance(objC, objP)).commitAllowingStateLoss();

                //MainFragment.callBack(11);
                break;
        }
    }


}
