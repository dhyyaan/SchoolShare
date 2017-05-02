package com.think360.schoolshare.api.post;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.interfaces.ServerCallback;
import com.think360.schoolshare.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by think360 on 03/03/17.
 */

public class ReportAbuse {
    private StringRequest sr;
    private Utils utils;
    private String status;

    public ReportAbuse(final Activity activity, final String user_id, final String msg, final ServerCallback callback)
    {

        utils= (Utils) activity.getApplicationContext();
        final ProgressDialog pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        sr = new StringRequest(Request.Method.POST, BaseUrl.BASE_URL+BaseUrl.abuse_report , new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if (pDialog.isShowing() && pDialog != null)
                {
                    pDialog.dismiss();
                }
                //   Log.d("respoce",response);
                try
                {


                    final JSONObject jsonObject = new JSONObject(response);
                    status = jsonObject.getString("status");
                    String    msg = jsonObject.getString("message");


                    if(status.equalsIgnoreCase("true") )
                    {
                        callback.onSuccess(status);
                        Custom_Toast(activity,msg);
                    }
                    else if(status.equalsIgnoreCase("false") )
                    {


                        Custom_Toast(activity,msg);
                    }else{

                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if (pDialog.isShowing() && pDialog != null)
                {
                    pDialog.dismiss();
                }
                if (error instanceof TimeoutError || error instanceof NoConnectionError)
                {
                    Custom_Toast(activity,""+error);
                }
                else if (error instanceof AuthFailureError)
                {
                    Custom_Toast(activity,""+error);
                }
                else if (error instanceof ServerError)
                {
                    Custom_Toast(activity,""+error);
                }
                else if (error instanceof NetworkError)
                {
                    Custom_Toast(activity,""+error);
                }
                else if (error instanceof ParseError)
                {
                    Custom_Toast(activity,""+error);
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {

                Map<String, String> params = new HashMap<>();

                params.put("user_id", user_id);
                params.put("msg", msg);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String>  params = new HashMap<>();
                params.put("Accept", "json");
                return params;
            }
        };
    }
    public void addQueue()
    {
        Utils.getInstance().addToRequestQueue(sr);
    }

    public void Custom_Toast(Activity activity,String abc)
    {
        Toast.makeText(activity,abc,Toast.LENGTH_SHORT).show();
       /* LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast,(ViewGroup) activity.findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(abc);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();*/
    }
}
