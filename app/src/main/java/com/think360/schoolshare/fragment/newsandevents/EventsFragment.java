package com.think360.schoolshare.fragment.newsandevents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.api.post.ReportAbuse;
import com.think360.schoolshare.interfaces.ServerCallback;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.surinder.utils.RootFragment;
import com.think360.schoolshare.surinder.utils.font.CenturyGothicBold;
import com.think360.schoolshare.surinder.utils.font.CenturyGothicRegular;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import rx.subjects.PublishSubject;


/**
 * Created by think360user on 11/2/17.
 */

public class EventsFragment extends RootFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    private String mid;
    private String mTitle;
    private String mType;
    private String mDescription;
    private String mStartDate;
    private String mEndDate;
    private String mPostedBy;
    private String mStatus;
    private String mLike;
    private String mComment;
    private String mAbuse;
    private JSONArray commentArray;
    private SliderLayout imageSlider;
    private AlertDialog deleteDialog;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param jsonObj Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsAndEventFragment.
     */
    // TODO: Rename and change types and number of parameters/**/
    public static EventsFragment newInstance(JSONObject jsonObj, String param2) {
        EventsFragment  fragment = new EventsFragment();
         Bundle args = new Bundle();


                //access event detail


        try {
             args.putString(ProfileConstent.ID, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.ID));
             args.putString(ProfileConstent.TITLE, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.TITLE));
             args.putString(ProfileConstent.TYPE, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.TYPE));
             args.putString(ProfileConstent.DESCRIPTION, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.DESCRIPTION));
             args.putString(ProfileConstent.START_DATE, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.START_DATE));
             args.putString(ProfileConstent.END_DATE, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.END_DATE));
             args.putString(ProfileConstent.POSTED_BY, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.POSTED_BY));
             args.putString(ProfileConstent.STATUS, jsonObj.getJSONObject("event_detail").getString(ProfileConstent.STATUS));
             args.putString(ProfileConstent.NO_LIKES, jsonObj.getJSONArray("likes").length()+"");
             args.putString(ProfileConstent.NO_COMMENTS, jsonObj.getJSONArray("comments").length()+"");
             args.putString(ProfileConstent.NO_ABUSE, jsonObj.getJSONArray("abuse_report").length()+"");
            //comments data
            fragment.commentArray = jsonObj.getJSONArray("comments");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Log.d("ddd ", getArguments().getString(ProfileConstent.ID));

    if (getArguments() != null) {
            mid = getArguments().getString(ProfileConstent.ID);
            mTitle = getArguments().getString(ProfileConstent.TITLE);
            mType = getArguments().getString(ProfileConstent.TYPE);
            mDescription = getArguments().getString(ProfileConstent.DESCRIPTION);
            mStartDate = getArguments().getString(ProfileConstent.START_DATE);
            mEndDate = getArguments().getString(ProfileConstent.END_DATE);
            mPostedBy = getArguments().getString(ProfileConstent.POSTED_BY);
            mStatus = getArguments().getString(ProfileConstent.START_DATE);
            mComment = getArguments().getString(ProfileConstent.NO_COMMENTS);
            mAbuse = getArguments().getString(ProfileConstent.NO_ABUSE);
            mLike = getArguments().getString(ProfileConstent.NO_LIKES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_event_detail, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CenturyGothicRegular type = (CenturyGothicRegular) view.findViewById(R.id.title);
        StringBuilder sb = new StringBuilder(mType);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        type.setText(sb);

        CenturyGothicBold eventTitle = (CenturyGothicBold)view.findViewById(R.id.eventTitle);
       eventTitle.setText(mTitle);
        CenturyGothicRegular byLayout = (CenturyGothicRegular)view.findViewById(R.id.byLayout);
        byLayout.setText(mPostedBy+" | "+mType);
        CenturyGothicRegular fromdate = (CenturyGothicRegular)view.findViewById(R.id.fromdate);
        fromdate.setText(mStartDate);
        CenturyGothicRegular todate = (CenturyGothicRegular)view.findViewById(R.id.todate);
        todate.setText(mEndDate);
        CenturyGothicRegular textView = (CenturyGothicRegular)view.findViewById(R.id.textView);
        textView.setText(mDescription);

        CenturyGothicRegular like = (CenturyGothicRegular)view.findViewById(R.id.like);
        like.setText(mLike+" Like");
        final CenturyGothicRegular comment = (CenturyGothicRegular)view.findViewById(R.id.comments);
        comment.setText(mComment+" Comments");
        CenturyGothicRegular abuse = (CenturyGothicRegular)view.findViewById(R.id.reportAbuse);
        abuse.setText("Report abuse");

        LinearLayout  llreportAbuse = (LinearLayout) view.findViewById(R.id.llreportAbuse);
        llreportAbuse.setOnClickListener(this);

        FrameLayout  fl = (FrameLayout) view.findViewById(R.id.back);
        fl.setOnClickListener(this);
        imageSlider = (SliderLayout)view.findViewById(R.id.slider);
        final LinearLayout comments = (LinearLayout)view.findViewById(R.id.linearLayout6);


        PublishSubject<Integer> subject = PublishSubject.create();
        subject.onNext(1);
        subject.subscribe(System.out::println);
        subject.onNext(2);
        subject.onNext(3);
        subject.onNext(4);


        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                // Store the Fragment in stack
                transaction.addToBackStack(null);

                if(Integer.parseInt(mComment) > 0) {
                    transaction.replace(R.id.fragContainer, CommentFragment.newInstance(commentArray, mType)).commitAllowingStateLoss();
                }

            }
        });

        sliderData();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       // MainActivitySurinder.self.onBackPressed();
     /*   if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
     /*   if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //  mListener = null;
    }
    private void  sliderData(){

       // HashMap<String,String> url_maps = new HashMap<>();
      //  url_maps.put("", "http://www.pachd.com/free-images/food-images/oranges-01.jpg");
        //url_maps.put("", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        //url_maps.put("", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
       // url_maps.put("", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<>();
        file_maps.put("",R.drawable.cc);
        file_maps.put("",R.drawable.bb);
        file_maps.put("",R.drawable.hh);
        file_maps.put("", R.drawable.rr);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //.setOnSliderClickListener(CommentFragment.this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            imageSlider.addSlider(textSliderView);
        }
        imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(2000);

    }
   @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
                //MainFragment.callBack(3);
                break;
            case R.id.llreportAbuse:
                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View reportAbuseView = factory.inflate(R.layout.dialog_reprt_abuse_layout, null);
                deleteDialog = new AlertDialog.Builder(getActivity()).create();
                deleteDialog.setView(reportAbuseView);
                reportAbuseView.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //your business logic
                        EditText reportAbuse = (EditText)reportAbuseView.findViewById(R.id.editTextReportAbuse);
                        new ReportAbuse(getActivity(),"1",reportAbuse.getText().toString(), new ServerCallback() {
                            @Override
                            public void onSuccess(String responce) {

                            }
                        }).addQueue();
                        deleteDialog.dismiss();
                    }
                });
                deleteDialog.show();
                break;
        }
    }


}
