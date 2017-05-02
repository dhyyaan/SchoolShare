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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;


import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.api.get.GetCity;
import com.think360.schoolshare.api.get.GetCountry;
import com.think360.schoolshare.api.get.GetState;
import com.think360.schoolshare.databinding.ContactInfoEditSubmitBinding;

import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.interfaces.ServerCallBackArray;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.model.ProfileModel;
import com.think360.schoolshare.surinder.utils.RootFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactInfoEditFragmentSubmit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfoEditFragmentSubmit extends RootFragment implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String ccurrentAdd;
    private String cCountryId;
    private String cCountry;
    private String cStateId;
    private String cState;
    private String cCityId;
    private String cCity;
    private String cPostalCode;
    private String cMobile;
    private String cPhone;
    private String cEmail;

    private String ppermanentAdd;
    private String pCountryId;
    private String pCountry;
    private String pStateId;
    private String pState;
    private String pCityId;
    private String pCity;
    private String pPostalCode;
    private String pMobile;
    private String pPhone;


    private LinearLayout permanentInfoLayout;
    private boolean isChecked;
    private String checkbox_is_same= "1";
    private Spinner spinnerCountry;
    private ArrayAdapter<String> countryAdapter,stateAdapter,cityAdapter;

    private EditText currentAdd,currentPostalAdd,currentMobile,currentPhone,currentEmail,permanentAdd,permanentPostalAdd,permanentMobile,permanentPhone;
    public static final JSONObject contactInfo = new JSONObject();
    private ArrayList<String> country_ids = new ArrayList<>();
    private ArrayList<String> countrys = new ArrayList<>();
    private ArrayList<String> state_ids = new ArrayList<>();
    private ArrayList<String> states = new ArrayList<>();
    private ArrayList<String> citys = new ArrayList<>();
    private ArrayList<String> city_ids = new ArrayList<>();
    private String current_country_id,current_state_id,current_city_id,permanent_country_id,permanent_state_id,permanent_city_id;
    private OnFragmentInteractionListener mListener;

    public ContactInfoEditFragmentSubmit() {
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
    public static ContactInfoEditFragmentSubmit newInstance(JSONObject obj, JSONObject obj2) {
        ContactInfoEditFragmentSubmit fragment = new ContactInfoEditFragmentSubmit();
        Bundle args = new Bundle();

        try {

            //Getting current Address
            args.putString(ProfileConstent.ADDRESS, obj.getString(ProfileConstent.ADDRESS));
            args.putString(ProfileConstent.COUNTRY_ID, obj.getString(ProfileConstent.COUNTRY_ID));
            args.putString(ProfileConstent.COUNTRY, obj.getString(ProfileConstent.COUNTRY));
            args.putString(ProfileConstent.STATE_ID, obj.getString(ProfileConstent.STATE_ID));
            args.putString(ProfileConstent.STATE, obj.getString(ProfileConstent.STATE));
            args.putString(ProfileConstent.CITY_ID, obj.getString(ProfileConstent.CITY_ID));
            args.putString(ProfileConstent.CITY, obj.getString(ProfileConstent.CITY));
            args.putString(ProfileConstent.POSTAL_CODE, obj.getString(ProfileConstent.POSTAL_CODE));
            args.putString(ProfileConstent.MOBILE, obj.getString(ProfileConstent.PHONE_NO));//mobile not coming from server
            args.putString(ProfileConstent.PHONE_NO, obj.getString(ProfileConstent.PHONE_NO));
            args.putString(ProfileConstent.EMAIL, obj.getString(ProfileConstent.EMAIL));


            //Getting permanent Address
            args.putString(ProfileConstent.PADDRESS, obj2.getString(ProfileConstent.ADDRESS));
            args.putString(ProfileConstent.PCOUNTRY_ID, obj2.getString(ProfileConstent.COUNTRY_ID));
            args.putString(ProfileConstent.PCOUNTRY, obj2.getString(ProfileConstent.COUNTRY));
            args.putString(ProfileConstent.PSTATE_ID, obj2.getString(ProfileConstent.STATE_ID));
            args.putString(ProfileConstent.PSTATE, obj2.getString(ProfileConstent.STATE));
            args.putString(ProfileConstent.PCITY_ID, obj2.getString(ProfileConstent.CITY_ID));
            args.putString(ProfileConstent.PCITY, obj2.getString(ProfileConstent.CITY));
            args.putString(ProfileConstent.PPOSTAL_CODE, obj2.getString(ProfileConstent.POSTAL_CODE));
            args.putString(ProfileConstent.PMOBILE, obj2.getString(ProfileConstent.PHONE_NO));//mobile not coming from server
            args.putString(ProfileConstent.PPHONE_NO, obj2.getString(ProfileConstent.PHONE_NO));


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
            this.cCountryId = getArguments().getString(ProfileConstent.COUNTRY_ID);
            this.cCountry = getArguments().getString(ProfileConstent.COUNTRY);
            this.cStateId = getArguments().getString(ProfileConstent.STATE_ID);
            this.cState = getArguments().getString(ProfileConstent.STATE);
            this.cCityId = getArguments().getString(ProfileConstent.CITY_ID);
            this.cCity = getArguments().getString(ProfileConstent.CITY);
            this.cPostalCode = getArguments().getString(ProfileConstent.POSTAL_CODE);
            this.cMobile = getArguments().getString(ProfileConstent.MOBILE);
            this.cPhone = getArguments().getString(ProfileConstent.PHONE_NO);
            this.cEmail= getArguments().getString(ProfileConstent.EMAIL);


            this.ppermanentAdd = getArguments().getString(ProfileConstent.PADDRESS);
            this.pCountryId = getArguments().getString(ProfileConstent.PCOUNTRY_ID);
            this.pCountry = getArguments().getString(ProfileConstent.PCOUNTRY);
            this.pStateId = getArguments().getString(ProfileConstent.PSTATE_ID);
            this.pState = getArguments().getString(ProfileConstent.PSTATE);
            this.pCityId = getArguments().getString(ProfileConstent.PCITY_ID);
            this.pCity = getArguments().getString(ProfileConstent.PCITY);
            this.pPostalCode = getArguments().getString(ProfileConstent.PPOSTAL_CODE);
            this.pMobile = getArguments().getString(ProfileConstent.PMOBILE);
            this.pPhone = getArguments().getString(ProfileConstent.PPHONE_NO);




        }
        countryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                countrys);
        new GetCountry(getActivity(), new ServerCallBackArray() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        country_ids.add(jsonObject.getString("id"));
                        countrys.add(jsonObject.getString("country"));
                        countryAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).addQueue();
        stateAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                states);




        cityAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                citys);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.contact_info_edit_submit, container, false);
        ContactInfoEditSubmitBinding binding = DataBindingUtil.inflate(inflater, R.layout.contact_info_edit_submit, container, false);
        ProfileModel profile = new ProfileModel(ccurrentAdd ,cCountryId,cCountry, cStateId, cState, cCityId, cCity, cPostalCode, cMobile,cPhone,cEmail,
                ppermanentAdd,pCountryId,pCountry,pStateId,pState,pCityId,pCity,pPostalCode,pMobile,pPhone);

        View view = binding.getRoot();
        binding.setProfile(profile);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout fl_back = (FrameLayout) view.findViewById(R.id.back);
          fl_back.setOnClickListener(this);

        //  ImageView edit_profile = (ImageView) view.findViewById(R.id.menu_edit);
        // edit_profile.setOnClickListener(this);
        permanentInfoLayout = (LinearLayout) view.findViewById(R.id.permanentInfo);
        CheckBox chkIsPermanent = (CheckBox) view.findViewById(R.id.chkIsPermanent);
        chkIsPermanent.setOnCheckedChangeListener(this);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);

        spinnerCountry.setAdapter(countryAdapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //   currentCountry = spinnerCountry.getSelectedItem().toString();
                current_country_id =  country_ids.get(i);
                new GetState(getActivity(),  country_ids.get(i), new ServerCallBackArray() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {

                        for(int i=0;i<jsonArray.length();i++){
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                state_ids.add(jsonObject.getString("id"));
                                states.add(jsonObject.getString("state"));

                                stateAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).addQueue();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final Spinner spinnerState = (Spinner) view.findViewById(R.id.spinnerState);
        spinnerState.setAdapter(stateAdapter);
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  currentState = spinnerState.getSelectedItem().toString();
                current_state_id =  state_ids.get(i);
                new GetCity(getActivity(),  state_ids.get(i), new ServerCallBackArray() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {
                       // Log.d("cityyyyy ",jsonArray+"");
                        for(int i=0;i<jsonArray.length();i++){
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //   state_ids.add(jsonObject.getString("id"));
                                citys.add(jsonObject.getString("city"));
                                city_ids.add(jsonObject.getString("id"));
                                cityAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).addQueue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final Spinner spinnerCity = (Spinner) view.findViewById(R.id.spinnerCity);
        spinnerCity.setAdapter(cityAdapter);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                current_city_id =  city_ids.get(i);
                 // currentCity = spinnerCity.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
              //  MainFragment.callBack(5);
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.isChecked = isChecked;
        final Spinner includeSpinnerCountry  = (Spinner)permanentInfoLayout.findViewById(R.id.spinnerCountry);
        final Spinner  includeSpinnerState  = (Spinner)permanentInfoLayout.findViewById(R.id.spinnerState);
        final Spinner  includeSpinnerCity  = (Spinner)permanentInfoLayout.findViewById(R.id.spinnerCity);

        if (this.isChecked) {

            permanentInfoLayout.setVisibility(View.GONE);
            includeSpinnerCountry.setAdapter(null);


            includeSpinnerState.setAdapter(null);


            includeSpinnerCity.setAdapter(null);
            checkbox_is_same="1";
        }
        else {
            permanentInfoLayout.setVisibility(View.VISIBLE);
            checkbox_is_same="0";
              includeSpinnerCountry.setAdapter(countryAdapter);


         includeSpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    permanent_country_id= country_ids.get(i);
                    // permanentCountry = includeSpinnerCountry.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            includeSpinnerState.setAdapter(stateAdapter);
            includeSpinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    permanent_state_id = state_ids.get(i);
                    //   permanentState = includeSpinnerState.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            includeSpinnerCity.setAdapter(cityAdapter);
            includeSpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    permanent_city_id =   city_ids.get(i);
                    //   permanentCity = includeSpinnerCity.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
    }
}