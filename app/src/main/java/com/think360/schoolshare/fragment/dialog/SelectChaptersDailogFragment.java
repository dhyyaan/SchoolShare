package com.think360.schoolshare.fragment.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.think360.schoolshare.api.get.GetChapters;
import com.think360.schoolshare.interfaces.OnDataBack;
import com.think360.schoolshare.interfaces.ServerCallBackArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.think360.schoolshare.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by think360user on 21/12/16.
 */
public class SelectChaptersDailogFragment extends DialogFragment {

private boolean select = false;
    private OnDataBack onDataBack;
    private ArrayList<String> chaptersa = new ArrayList<>();
    private ArrayList<String> chapters_ids = new ArrayList<>();

    public void setCallBack(OnDataBack ondate) {
        this.onDataBack = ondate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.select_chapters_dailog_fragment_content, container,
                false);

        //getDialog().setTitle("DialogFragment");
        setCancelable(false);


        // Do something else

        final Spinner spinnerSelectChapter = (Spinner) rootView.findViewById(R.id.spinnerSelectChapter);
        final ArrayAdapter<String> industryAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                chaptersa);
        new GetChapters(getActivity(), new ServerCallBackArray() {
            @Override
            public void onSuccess(JSONArray jsonArray) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //   state_ids.add(jsonObject.getString("id"));
                        chaptersa.add(jsonObject.getString("chapter_name"));
                        chapters_ids.add(jsonObject.getString("id"));
                        industryAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).addQueue();

        spinnerSelectChapter.setAdapter(industryAdapter);

        spinnerSelectChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
               if(select) {
                   String items = spinnerSelectChapter.getSelectedItem().toString();
                   onDataBack.onSet(chapters_ids.get(arg2), items);
               }
                select = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        ImageView ivDailogCancel = (ImageView) rootView.findViewById(R.id.ivDailogCancel);
        ivDailogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dismiss();
            }
        });
        return rootView;
    }


}