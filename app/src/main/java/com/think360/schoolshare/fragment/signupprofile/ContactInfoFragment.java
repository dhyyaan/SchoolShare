package com.think360.schoolshare.fragment.signupprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.SignUpActivity;
import com.think360.schoolshare.api.get.GetCity;
import com.think360.schoolshare.api.get.GetCountry;
import com.think360.schoolshare.api.get.GetState;
import com.think360.schoolshare.interfaces.ServerCallBackArray;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.utils.SharePreferenceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by think360user on 19/12/16.
 */
public class ContactInfoFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {


    private ImageView imageViewStep3;
    private View line2;

    private ArrayList<String> country_ids = new ArrayList<>();
    private ArrayList<String> countrys = new ArrayList<>();
    private ArrayList<String> state_ids = new ArrayList<>();
    private ArrayList<String> states = new ArrayList<>();
    private ArrayList<String> citys = new ArrayList<>();
    private ArrayList<String> city_ids = new ArrayList<>();

    private ArrayAdapter<String> countryAdapter, stateAdapter, cityAdapter;
    //  private LinearLayout permanentInfoLayout;
    private EditText currentAdd, currentPostalCode, currentMobile, currentEmail;

    // newInstance constructor for creating fragment with arguments
    public static ContactInfoFragment newInstance() {


        return new ContactInfoFragment();
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                countrys);
        new GetCountry(getActivity(), new ServerCallBackArray() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
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

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.contact_info_fragment, container, false);
        imageViewStep3 = (ImageView) getActivity().findViewById(R.id.imageViewStep3);
        line2 = getActivity().findViewById(R.id.line2);

        currentAdd = (EditText) view.findViewById(R.id.etCurrentAdd);
        Spinner spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);

        spinnerCountry.setAdapter(countryAdapter);
        //   int pos = countryAdapter.getPosition(Locale.getDefault().getDisplayCountry());

        //  spinnerCountry.setSelection(pos);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //   currentCountry = spinnerCountry.getSelectedItem().toString();
                PersonalInfoFragment.params.put("country_id", country_ids.get(i));

                new GetState(getActivity(), country_ids.get(i), new ServerCallBackArray() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {

                        for (int i = 0; i < jsonArray.length(); i++) {
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
                PersonalInfoFragment.params.put("state_id", state_ids.get(i));

                new GetCity(getActivity(), state_ids.get(i), new ServerCallBackArray() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {

                        for (int i = 0; i < jsonArray.length(); i++) {
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
                PersonalInfoFragment.params.put("city_id", city_ids.get(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        currentPostalCode = (EditText) view.findViewById(R.id.etPostalCode);
        currentMobile = (EditText) view.findViewById(R.id.etMobile);
        currentEmail = (EditText) view.findViewById(R.id.etEmail);


        final CheckBox chkIsPermanent = (CheckBox) view.findViewById(R.id.chkIsPermanent);
        chkIsPermanent.setOnCheckedChangeListener(this);

        AppCompatButton buttonNext = (AppCompatButton) view.findViewById(R.id.btnNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentEmail.getText().length() > 0) {
                    imageViewStep3.setImageResource(R.mipmap.step_3_2);
                    line2.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color1));
                    ((SignUpActivity) getContext()).setCurrentPosition(2);

                    PersonalInfoFragment.params.put("address", currentAdd.getText().toString());
                    PersonalInfoFragment.params.put("postal_code", currentPostalCode.getText().toString());
                    PersonalInfoFragment.params.put("mobile", currentMobile.getText().toString());
                    // PersonalInfoFragment.params.put("current_phone", currentPhone.getText().toString());
                    PersonalInfoFragment.params.put("email", currentEmail.getText().toString());

             /*   for (Map.Entry<String,String> entry : PersonalInfoFragment. params.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        // do stuff
                        Log.d(key,value);
                    }*/
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.text_empty_email), Toast.LENGTH_SHORT).show();
                }
            }
        });

        onFacebookLogin();

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        PersonalInfoFragment.params.put("checkbos_is_same", String.valueOf(isChecked));

    }

    public void onFacebookLogin() {
        //setFacebook profile data
        if (SharePreferenceData.getString(getActivity(), ProfileConstent.ISLOGIN, null).equals("true")) {
            //Log.d("share",SharePreferenceData.getString(getActivity(), "first_name", null)+"  "+SharePreferenceData.getString(getActivity(), "last_name", null));

            currentEmail.setText(SharePreferenceData.getString(getActivity(), ProfileConstent.EMAIL, null));


        }
    }

}