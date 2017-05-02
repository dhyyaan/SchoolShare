package com.think360.schoolshare.api.get;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.think360.schoolshare.baseurl.BaseUrl;
import com.think360.schoolshare.interfaces.ServerCallBackObj2;
import com.think360.schoolshare.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by think360 on 01/03/17.
 */

public class GetNewsEventsDetails {
    private RequestQueue queue;
    private StringRequest sr;
    private Utils customFont;

   // ProgressDialog pDialog;

    public GetNewsEventsDetails(final Context context, String event_id, final ServerCallBackObj2 callback)
    {
      //  pDialog = new ProgressDialog(context);
      // pDialog.setMessage("Loading...");
      // pDialog.setCancelable(false);
      // pDialog.setCanceledOnTouchOutside(false);
       // pDialog.show();
        customFont = (Utils) context.getApplicationContext();
        queue = Volley.newRequestQueue(context);
        //   Log.d("states ",BaseUrl.BASE_URL+BaseUrl.get_city+BaseUrl.state_id+state_id);
        sr = new StringRequest(Request.Method.GET, BaseUrl.BASE_URL2+BaseUrl.get_event_detail+BaseUrl.event_id+event_id, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
             // if (pDialog.isShowing() && pDialog != null) {
              //     pDialog.dismiss();
              // }
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    //   callback.onSuccess(new JSONArray(response));
                    // String status = jsonObject.getString("status");

                    if (jsonObject.getString("status").equalsIgnoreCase("true"))
                    {
                        callback.onSuccess(jsonObject);
                        //callback.onSuccess1(jsonObject.getJSONArray("event_detail"));

                        //callback.onSuccess2(jsonObject.getJSONArray("comments"));

                       // callback.onSuccess3(jsonObject.getJSONArray("abuse_report"));

                        //callback.onSuccess4(jsonObject.getJSONArray("likes"));


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
             // if (pDialog.isShowing() && pDialog != null) {
              //    pDialog.dismiss();
             //  }
              //  if (error instanceof NoConnectionError) {
                    Toast.makeText(context,""+error, Toast.LENGTH_SHORT).show();
              //  }
            }
        }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
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
