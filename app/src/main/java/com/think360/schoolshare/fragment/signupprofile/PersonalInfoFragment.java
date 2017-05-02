package com.think360.schoolshare.fragment.signupprofile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.SignUpActivity;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.fragment.dialog.DatePickerFragment;
import com.think360.schoolshare.interfaces.MultipartProgressListener;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.utils.AppHelper;
import com.think360.schoolshare.utils.SharePreferenceData;
import com.think360.schoolshare.utils.Utils;
import com.think360.schoolshare.utils.VolleyMultipartRequest;
import com.think360.schoolshare.utils.imagecompressor.Compressor;
import com.think360.schoolshare.utils.imagecompressor.FileUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by think360user on 19/12/16.
 */
public class PersonalInfoFragment extends Fragment  {

    protected static Map<String, String> params = new HashMap<>();
    // Store instance variables
    private View line1;


    private static final int RESULT_OK = -1;
    private static int RESULT_LOAD_IMAGE = 1;

    private ImageView imageViewStep1, imageViewStep2, imageUploaded;
    private EditText firstName, middleName, lastName, nickName, dob;
    //   private RadioGroup radioGroupGender,radioButtoRoleInSchool;
    private File file = null;
    private TextView tvLvlUploadPic;
    private int total_bytes;
    // newInstance constructor for creating fragment with arguments
    public static PersonalInfoFragment newInstance() {

        return new PersonalInfoFragment();
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.personal_info_fragment, container, false);
        // PersonalInfoFragmentBinding binding = PersonalInfoFragmentBinding.inflate(getActivity().getLayoutInflater());


        //View view = binding.getRoot();
        //  binding.setProfile(profile);
        imageViewStep1 = (ImageView) getActivity().findViewById(R.id.imageViewStep1);
        imageViewStep1.setImageResource(R.mipmap.step_1_2);
        imageViewStep2 = (ImageView) getActivity().findViewById(R.id.imageViewStep2);
        line1 = getActivity().findViewById(R.id.line1);
        //      CircleProgress circle_progress  = (CircleProgress)view.findViewById(R.id.circle_progress);
        //     circle_progress.setProgress((int)(Math.random() * 100));

        final Spinner spinnerProfile = (Spinner) view.findViewById(R.id.spinnerUseProfileAs);

        spinnerProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                params.put("profile_as", spinnerProfile.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });   //  arcProgress1.setProgress((int)(Math.random() * 100));
        firstName = (EditText) view.findViewById(R.id.etFirstName);
        middleName = (EditText) view.findViewById(R.id.etMiddleName);
        lastName = (EditText) view.findViewById(R.id.etLastName);
        nickName = (EditText) view.findViewById(R.id.etNickName);

        RadioGroup radioGroupGender = (RadioGroup) view.findViewById(R.id.radioGroupGender);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonMale:
                        // do operations specific to this selection
                        RadioButton radioMale = (RadioButton) view.findViewById(checkedId);
                        params.put("gender", radioMale.getText().toString());

                        break;
                    case R.id.radioButtonFemale:
                        // do operations specific to this selection
                        RadioButton radioFemale = (RadioButton) view.findViewById(checkedId);

                        params.put("gender", radioFemale.getText().toString());

                        break;

                }
                //  Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
        RadioGroup radioButtoRoleInSchool = (RadioGroup) view.findViewById(R.id.radioButtoRoleInSchool);
        radioButtoRoleInSchool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonStu:
                        // do operations specific to this selection

                        params.put("role_id", "2");

                        break;
                    case R.id.radioButtonStaff:
                        // do operations specific to this selection

                        params.put("role_id", "3");

                        break;

                }

            }
        });
        dob = (EditText) view.findViewById(R.id.etDob);

        Spinner spinnerMaterialStatus = (Spinner) view.findViewById(R.id.spinnerMaterialStatus);

        spinnerMaterialStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                params.put("marital_status", spinnerMaterialStatus.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imageUploaded = (ImageView) view.findViewById(R.id.imageUploaded);
        tvLvlUploadPic = (TextView) view.findViewById(R.id.tvLvlUploadPic);
        final EditText etDob = (EditText) view.findViewById(R.id.etDob);

        ImageView ivCalender = (ImageView) view.findViewById(R.id.ivCalender);
        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment date = new DatePickerFragment();
                /**
                 * Set Up Current Date Into dialog
                 */
                Calendar calender = Calendar.getInstance();
                Bundle args = new Bundle();
                args.putInt("year", calender.get(Calendar.YEAR));
                args.putInt("month", calender.get(Calendar.MONTH));
                args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
                date.setArguments(args);
                /**
                 * Set Call back to capture selected date
                 */
                date.setCallBack(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        etDob.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth));
                    }
                });
                date.show(getActivity().getSupportFragmentManager(), "Date Picker");
            }
        });


        imageUploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });


        AppCompatButton buttonNext = (AppCompatButton) view.findViewById(R.id.btnNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Toast.makeText(getActivity(), ""+total_bytes, Toast.LENGTH_SHORT).show();

                if (firstName.getText().length() > 0 && lastName.getText().length() > 0) {
                    imageViewStep2.setImageResource(R.mipmap.step_2_2);
                    line1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color1));
                    ((SignUpActivity) getContext()).setCurrentPosition(1);

                    params.put("first_name", firstName.getText().toString());
                    params.put("middle_name", middleName.getText().toString());
                    params.put("last_name", lastName.getText().toString());
                    params.put("nick_name", nickName.getText().toString());
                    params.put("date_of_birth", dob.getText().toString());


               /*     for (Map.Entry<String,String> entry : params.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        // do stuff
                        Log.d(key,value);
                    }

                    */


                } else {

                    Toast.makeText(getActivity(), getResources().getString(R.string.text_empty_fname), Toast.LENGTH_SHORT).show();
                }

            }


        });

        onFacebookLogin(radioGroupGender, spinnerMaterialStatus);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //GETTING IMAGE FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {


            try {
                file = FileUtil.from(getActivity(), data.getData());
                compressImage(file);




            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void saveProfileFile(final String fileName) {
        // loading or check internet connection or something...
        // ... then

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, BaseUrl.BASE_URL + BaseUrl.upload_image, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                //  Log.d("result ",response);
                try {
                    JSONObject result = new JSONObject(new String(response.data));

                    if (result.getString("status").equals("true")) {
                        // tell everybody you have succed upload image and post strings
                        //  Log.i("Messsage", message);
                        if (!SharePreferenceData.getString(getActivity(), ProfileConstent.ISLOGIN, null).equals("true")) {
                            imageUploaded.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                        }

                        tvLvlUploadPic.setText(R.string.txt_image_uploaded);
                        params.put("profile_pic", result.getString("image_id"));
                        //   imageId.put("image_id",image_id);
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

                params.put("name", fileName);

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                if (SharePreferenceData.getString(getActivity(), ProfileConstent.ISLOGIN, null).equals("true")) {

                    params.put("image", new DataPart(fileName, AppHelper.getFileDataFromDrawable(getActivity(), imageUploaded.getDrawable()), "image/*"));

                   // total_bytes = ByteBuffer.wrap(AppHelper.getFileDataFromDrawable(getActivity(), imageUploaded.getDrawable())).getInt();
                } else {
                    params.put("image", new DataPart(fileName, AppHelper.getFileDataFromDrawable(getActivity(), Drawable.createFromPath(String.valueOf(file))), "image/*"));
                   // total_bytes = ByteBuffer.wrap(AppHelper.getFileDataFromDrawable(getActivity(), Drawable.createFromPath(String.valueOf(file)))).getInt();
                }

                //   params.put("image", new DataPart(fileName, AppHelper.getFileDataFromDrawable(getActivity(), imageUploaded.getDrawable()), "image/*"));


                return params;
            }
        } ;

        // VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);

        Utils.getInstance().addToRequestQueue(multipartRequest);
         //. getContent()
    }

    public void compressImage(File actualImage) {
        if (actualImage == null) {

            //  showError("Please choose an image!");
        } else {

            // Compress image using RxJava in background thread
            Compressor.getDefault(getActivity())
                    .compressToFileAsObservable(actualImage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<File>() {
                        @Override
                        public void call(File file) {
                            //  compressedImage = file;
                            total_bytes = AppHelper.getFileDataFromDrawable(getActivity(), Drawable.createFromPath(String.valueOf(file))).length;
                            int index = file.toString().lastIndexOf('/');
                            saveProfileFile(file.toString().substring(index + 1, file.toString().length()));
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            // showError(throwable.getMessage());
                        }
                    });
        }
    }


    public void onFacebookLogin(RadioGroup radioGroupGender, Spinner spinnerMaterialStatus) {
        //setFacebook profile data
        if (SharePreferenceData.getString(getActivity(), ProfileConstent.ISLOGIN, null).equals("true")) {

            try {
                firstName.setText(SharePreferenceData.getString(getActivity(), ProfileConstent.FIRST_NAME, null));
                lastName.setText(SharePreferenceData.getString(getActivity(), ProfileConstent.LAST_NAME, null));
                dob.setText(SharePreferenceData.getString(getActivity(), ProfileConstent.BIRTHDAY, null));

                if (SharePreferenceData.getString(getActivity(), ProfileConstent.GENDER, null).equalsIgnoreCase("Male")) {
                    ((RadioButton) radioGroupGender.findViewById(R.id.radioButtonMale)).setChecked(true);

                } else if (SharePreferenceData.getString(getActivity(), ProfileConstent.GENDER, null).equalsIgnoreCase("Female")) {
                    ((RadioButton) radioGroupGender.findViewById(R.id.radioButtonFemale)).setChecked(true);
                } else {}
                spinnerMaterialStatus.setSelection(((ArrayAdapter<String>) spinnerMaterialStatus.getAdapter()).getPosition(SharePreferenceData.getString(getActivity(), ProfileConstent.MATERIAL_STATUS, null)), true);
                URL pic = new URL(SharePreferenceData.getString(getActivity(), ProfileConstent.PICTURE, null));
                imageUploaded.setImageBitmap(BitmapFactory.decodeStream(pic.openConnection().getInputStream()));
                total_bytes = ByteBuffer.wrap(AppHelper.getFileDataFromDrawable(getActivity(), imageUploaded.getDrawable())).getInt();
                saveProfileFile("fb_profile_image");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


       /*    ApiCountryInterface apiService =
                        ApiClient.getClient().create(ApiCountryInterface.class);

          Call<CSCResponce> call = apiService.getCountry();
                call.enqueue(new Callback<CSCResponce>() {
                   @Override
                    public void onResponse(Call<CSCResponce>call, retrofit2.Response<CSCResponce> response) {
                      List<CountryModel> movies = response.body().getResults();
                        Log.d("TAG", "all country received: " + movies.size());
                    }



                    @Override
                   public void onFailure(Call<CSCResponce>call, Throwable t) {
                        // Log error here since request failed
                        Log.e("TAG", t.toString());
                   }
                });*/

}