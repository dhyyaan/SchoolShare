package com.think360.schoolshare.handlers;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
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
import com.think360.schoolshare.api.post.PostPersonalInfo;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.fragment.dialog.DatePickerFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragmentSubmit;
import com.think360.schoolshare.interfaces.MultipartProgressListener;
import com.think360.schoolshare.interfaces.ServerCallback;
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
 * Created by think360 on 10/03/17.
 */

public class MyHandlers {

    private static final int RESULT_OK = -1;
    private static int RESULT_LOAD_IMAGE = 1;
    private Map<String,String> pInfomap = new HashMap<>();

    //Goes on previous fragment
    public void onClickBack(View view) {
        MainActivitySurinder.self.onBackPressed();
    }
    //Sets Date on Edit Text in PersonalInfoEditSubmit Fragment
    public void onClickDateSet(View view) {
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

                        PersonalInfoEditFragmentSubmit. binding.etDob.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth));
                    }
                });
                date.show(MainActivitySurinder.self.getSupportFragmentManager(), "Date Picker");
            }
            //Sets profile image from gallary to server in PersonalInfoEditSubmitFragment
  // public void onClickProfileImageSet(View view) {

   //    MainActivitySurinder.self.startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
   // }



    //Gets Gender value in personal info edit fragment
    public void  onCheckedGenderChanged (RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radioButtonMale:
                // do operations specific to this selection


                pInfomap.put("gender", PersonalInfoEditFragmentSubmit.binding.radioButtonMale.getText().toString());

                break;
            case R.id.radioButtonFemale:
                // do operations specific to this selection

                pInfomap.put("gender", PersonalInfoEditFragmentSubmit.binding.radioButtonFemale.getText().toString());

                break;
        }
        }

    //Gets Gender role in school in personal info edit fragment
    public void  onCheckedRoleInSchChanged (RadioGroup group, int checkedId) {

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
    }
   public void onItemSelectedProfile(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(view.getContext(),""+ adapterView.getSelectedItem(), Toast.LENGTH_SHORT).show();
        pInfomap.put("profile_as",adapterView.getSelectedItem().toString());
    }
    public void onItemSelectedMaterialStatus(AdapterView<?> adapterView, View view, int i, long l) {
        // Toast.makeText(view.getContext(), "onItemSelected "+adapterView.getSelectedItem(), Toast.LENGTH_SHORT).show();
        pInfomap.put("marital_status",adapterView.getSelectedItem().toString());
    }
    public void onClickSubmit(View view) {


      //  Toast.makeText(view.getContext(), "On Long Click Listener", Toast.LENGTH_SHORT).show();
        if (PersonalInfoEditFragmentSubmit.binding.etFirstName.getText().length() > 0 && PersonalInfoEditFragmentSubmit.binding.etLastName.getText().length() > 0) {


            pInfomap.put("first_name", PersonalInfoEditFragmentSubmit.binding.etFirstName.getText().toString());
            pInfomap.put("middle_name",PersonalInfoEditFragmentSubmit.binding.etMiddleName.getText().toString());
            pInfomap.put("last_name", PersonalInfoEditFragmentSubmit.binding.etLastName.getText().toString());
            pInfomap.put("nick_name",PersonalInfoEditFragmentSubmit. binding.etNickName.getText().toString());
            pInfomap.put("date_of_birth", PersonalInfoEditFragmentSubmit.binding.etDob.getText().toString());
            pInfomap.put("additional_info",PersonalInfoEditFragmentSubmit. binding.etadditionalInfo.getText().toString());



            Log.e("current add ", pInfomap.toString());


        } else {

            Toast.makeText(MainActivitySurinder.self,view.getContext(). getResources().getString(R.string.text_empty_fname), Toast.LENGTH_SHORT).show();
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //GETTING IMAGE FROM GALLERY
        try {
                File file = FileUtil.from(MainActivitySurinder.self, data.getData());
                compressImage(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void compressImage(File actualImage) {
        if (actualImage == null) {
            //  showError("Please choose an image!");
        } else {


            // Compress image using RxJava in background thread
            Compressor.getDefault(MainActivitySurinder.self)
                    .compressToFileAsObservable(actualImage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<File>() {


                        @Override
                        public void call(File file) {
                            //  compressedImage = file;

                            int index = file.toString().lastIndexOf('/');
                            saveProfileFile(file,file.toString().substring(index + 1, file.toString().length()));

                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            // showError(throwable.getMessage());
                        }
                    });
        }
    }

    private void saveProfileFile(File file,final String fileName) {
        // loading or check internet connection or something...
        // ... then

        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, BaseUrl.BASE_URL + BaseUrl.upload_image, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {

                String resultResponse = new String(response.data);
                //  Log.d("result ",resultResponse);
                try {
                    JSONObject result = new JSONObject(resultResponse);


                    if (result.getString("status").equals("true")) {
                       // Log.d("image ",result.getString("image_id"));
                        PersonalInfoEditFragmentSubmit.binding.imageUploaded.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                        pInfomap.put("image_id",result.getString("image_id"));


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
                params.put("image", new DataPart(fileName, AppHelper.getFileDataFromDrawable(MainActivitySurinder.self, Drawable.createFromPath(String.valueOf(file))), "image/*"));
                //     params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(this, mCoverImage.getDrawable()), "image/jpeg"));

                return params;
            }
        };

        // VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
        Utils.getInstance().addToRequestQueue(multipartRequest);
    }




}

