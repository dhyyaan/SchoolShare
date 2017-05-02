package com.think360.schoolshare.fragment.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.think360.schoolshare.R;

/**
 * Created by think360user on 21/12/16.
 */
public class RequestSentSuccessfully extends DialogFragment {
    // View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     //   getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        View rootView = inflater.inflate(R.layout.request_send_successfully_content, container,
                false);
        getDialog().setTitle("DialogFragment");
        setCancelable(false);



        return rootView;
    }

}