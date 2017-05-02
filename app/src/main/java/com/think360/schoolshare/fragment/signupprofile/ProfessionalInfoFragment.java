package com.think360.schoolshare.fragment.signupprofile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.think360.schoolshare.R;
import com.think360.schoolshare.api.get.GetIndustry;
import com.think360.schoolshare.api.post.SendRegistrationData;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.fragment.dialog.RequestSentSuccessfully;
import com.think360.schoolshare.interfaces.MultipartProgressListener;
import com.think360.schoolshare.interfaces.ServerCallBackArray;
import com.think360.schoolshare.interfaces.ServerCallback;
import com.think360.schoolshare.utils.AppHelper;
import com.think360.schoolshare.utils.Utils;
import com.think360.schoolshare.utils.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by think360user on 9/1/17.
 */
public class ProfessionalInfoFragment extends Fragment {

    //private ArrayList<String> country_ids = new ArrayList<>();
    private ArrayList<String> industries = new ArrayList<>();
    private static final int FILE_SELECT_CODE = 3;
    private static final int RESULT_OK = -1;
    // private  static JSONArray registrationJsonArray = new JSONArray();
    //  public static JSONObject professionalInfo = new JSONObject();
    //  public static JSONObject chapters = new JSONObject();

    private ArrayAdapter<String> industryAdapter;
    FragmentManager fm;
    // private String industry;
    private EditText organizationName, designation, linkdinProfileLink, additionalInfo;
    private Handler handle = new Handler();

    // newInstance constructor for creating fragment with arguments
    public static ProfessionalInfoFragment newInstance() {


        return new ProfessionalInfoFragment();
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        industryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                industries);
        new GetIndustry(getActivity(), new ServerCallBackArray() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        industries.add(jsonObject.getString("name"));
                        industryAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).addQueue();

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.professional_info_fragment, container, false);
        organizationName = (EditText) view.findViewById(R.id.etOrganizationName);
        final Spinner spinnerIndustrySpinner = (Spinner) view.findViewById(R.id.spinnerIndustry);
        spinnerIndustrySpinner.setAdapter(industryAdapter);

        spinnerIndustrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PersonalInfoFragment.params.put("industry", spinnerIndustrySpinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //  sector = (EditText)view.findViewById(R.id.etSector);
        designation = (EditText) view.findViewById(R.id.etDesignation);
        linkdinProfileLink = (EditText) view.findViewById(R.id.etLinkdinProfileLink);
        additionalInfo = (EditText) view.findViewById(R.id.etadditionalInfo);
        AppCompatButton buttonFinish = (AppCompatButton) view.findViewById(R.id.btnFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonalInfoFragment.params.put("organization_name", organizationName.getText().toString());
                PersonalInfoFragment.params.put("designation", designation.getText().toString());
                PersonalInfoFragment.params.put("linkdin_profile_link", linkdinProfileLink.getText().toString());
                PersonalInfoFragment.params.put("additional_infop", additionalInfo.getText().toString());

             /*   for (Map.Entry<String,String> entry : PersonalInfoFragment. params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    // do stuff
                    Log.d(key,value);
                }*/


                for (int i = 0; i < AcademicInfoFragment.chapterListIds.size(); i++) {

                    PersonalInfoFragment.params.put("chapter_id_" + AcademicInfoFragment.chapterListIds.get(i), AcademicInfoFragment.chapterListIds.get(i));


                }

                //  registrationJsonArray.put(chapters);
                //  registrationJsonArray.put(professionalInfo);


                new SendRegistrationData(getActivity(), PersonalInfoFragment.params, new ServerCallback() {
                    @Override
                    public void onSuccess(String responce) {
                        if (responce.equalsIgnoreCase("true")) {
                            fm = getActivity().getSupportFragmentManager();
                            RequestSentSuccessfully dFragment = new RequestSentSuccessfully();
                            // Show DialogFragment
                            dFragment.show(fm, "Dialog Fragment");
                            handle.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getActivity().finish();

                                }
                            }, 3000);

                        }

                    }
                }).addQueue();


            }
        });
        AppCompatButton btnUploadFile = (AppCompatButton) view.findViewById(R.id.btnUploadFile);
        btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        return view;
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, FILE_SELECT_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Toast.makeText(getActivity(),resultCode+" "+requestCode,Toast.LENGTH_SHORT).show();

        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();

                    try {
                        // Toast.makeText(getActivity(), getPath(getActivity(),uri),Toast.LENGTH_SHORT).show();
                        saveProfileFile(getPath(getActivity(), uri));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveProfileFile(final String fileName) {
        final int index = fileName.lastIndexOf('/');

        //  Log.d("dd ",fileName.substring(index+1,fileName.length()));
        // loading or check internet connection or something...
        // ... then

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, BaseUrl.BASE_URL + BaseUrl.upload_resume, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                //  Log.d("result ",resultResponse);
                try {
                    JSONObject result = new JSONObject(resultResponse);

                    if (result.getString("status").equals("true")) {
                        // tell everybody you have succed upload image and post strings
                        //  Log.i("Messsage", message);
                        PersonalInfoFragment.params.put("resume", result.getString("resume_id"));
                    } else {
                        //  Log.i("Unexpected", message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Log.e("Error Status", status);
                        Log.e("Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message + " Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message + " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message + " Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        },new MultipartProgressListener() {
            @Override
            public void transferred(long transfered, int total) {
                Log.d("progess",transfered+"   "+total);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                //    params.put("name", "mNameInput.getText().toString()");

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                //     params.put("resume", new DataPart("",fileName ,"*/*"));

                try {
                    params.put("resume", new DataPart(fileName.substring(index + 1, fileName.length()), AppHelper.getFileBytes(new File(fileName)), "*/*"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return params;
            }
        };

        // VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
        Utils.getInstance().addToRequestQueue(multipartRequest);
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


}