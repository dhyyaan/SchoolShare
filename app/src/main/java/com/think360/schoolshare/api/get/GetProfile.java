package com.think360.schoolshare.api.get;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.interfaces.ServerCallBackArray;
import com.think360.schoolshare.interfaces.ServerCallBackObj;
import com.think360.schoolshare.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by think360 on 17/02/17.
 */

public class GetProfile {

        private RequestQueue queue;
        private StringRequest sr;
        private Utils customFont;

        ProgressDialog pDialog;

        public GetProfile(final Context context, String user_id, final ServerCallBackObj callback)
        {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
            customFont = (Utils) context.getApplicationContext();
            queue = Volley.newRequestQueue(context);
            //   Log.d("states ",BaseUrl.BASE_URL+BaseUrl.get_city+BaseUrl.state_id+state_id);
            sr = new StringRequest(Request.Method.GET, BaseUrl.BASE_URL+BaseUrl.get_member_detail+BaseUrl.user_id+user_id, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    if (pDialog.isShowing() && pDialog != null) {
                        pDialog.dismiss();
                    }
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                     //   callback.onSuccess(new JSONArray(response));
                   // String status = jsonObject.getString("status");

                    if (jsonObject.getString("status").equalsIgnoreCase("true"))
                    {
                        callback.onSuccess1(jsonObject.getJSONArray("user_detail").getJSONObject(0));


                        callback.onSuccess2(jsonObject.getJSONArray("user_address").getJSONObject(0));

                        callback.onSuccess3(jsonObject.getJSONArray("user_address").getJSONObject(1));

                        callback.onSuccess4(jsonObject.getJSONObject("image_detail"));
                    }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (pDialog.isShowing() && pDialog != null) {
                        pDialog.dismiss();
                    }
                    if (error instanceof NoConnectionError) {
                        Toast.makeText(context,""+error, Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    return params;
                }
            };
        }

    public void addQueue()
    {
        queue.add(sr);
        sr.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
