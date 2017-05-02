package com.think360.schoolshare.fragment.newsandevents;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.adapter.CommentAdapter;
import com.think360.schoolshare.model.CommentItem;
import com.think360.schoolshare.model.ProfileConstent;
import com.think360.schoolshare.surinder.utils.RootFragment;
import com.think360.schoolshare.surinder.utils.font.CenturyGothicRegular;
import com.think360.schoolshare.utils.Time;
import com.think360.schoolshare.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by think360user on 1/2/17.
 */

public class CommentFragment extends RootFragment implements View.OnClickListener {
    private RecyclerView commentRecycler;
    private CommentAdapter commentadapter;
    private List<CommentItem> commentList;
    private SliderLayout imageSlider;

    private  FrameLayout fragContainer;
    private ScrollView scrollviewComments;
    private JSONArray jsonArray;
    private String mType;
    public static CommentFragment newInstance(JSONArray array, String title) {
        CommentFragment  fragment = new CommentFragment();
        fragment.jsonArray = array;
        fragment.mType = title;
       // Log.d("dddddd ",fragment.jsonArray+"");
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentList = new ArrayList<>();
     for(int i=0;i<jsonArray.length();i++){
          try {
             // Date date =  new Date(jsonArray.getJSONObject(i).getString(ProfileConstent.CREATED_AT));
               commentList.add(new CommentItem(jsonArray.getJSONObject(i).getString(ProfileConstent.ID), jsonArray.getJSONObject(i).getString(ProfileConstent.ID), Time.printDateDifference(jsonArray.getJSONObject(i).getString(ProfileConstent.CREATED_AT)), jsonArray.getJSONObject(i).getString(ProfileConstent.COMMENT), R.mipmap.user));
          } catch (JSONException e) {
               e.printStackTrace();
            }

        }
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.comment_fragment, container, false);

         fragContainer = (FrameLayout) view.findViewById(R.id.fragContainer);


        CenturyGothicRegular type = (CenturyGothicRegular) view.findViewById(R.id.title);
        StringBuilder sb = new StringBuilder(mType);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        type.setText(sb);

        CenturyGothicRegular title_comments = (CenturyGothicRegular) view.findViewById(R.id.title_comments);
        title_comments.setText(commentList.size()+" Comments");

        FrameLayout fl = (FrameLayout) view.findViewById(R.id.back);
        fl.setOnClickListener(this);


        imageSlider = (SliderLayout) view.findViewById(R.id.slider);

        commentRecycler = (RecyclerView) view.findViewById(R.id.comment_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        commentRecycler.setLayoutManager(mLayoutManager);


        scrollviewComments = (ScrollView) view.findViewById(R.id.scrollviewComments);


       // commentList = new ArrayList<>();

       // commentList.add(new CommentItem("1", "Samual Dahir", "18 days ago", "Print Friendly Version of this pagePrint Get a PDF version of this webpagePDF What this handout is about Friendly Version of this pagePrint Get a PDF", R.mipmap.user));
        //commentList.add(new CommentItem("2", "Samual Dahir", "18 days ago", "Print Friendly Version of this pagePrint Get a PDF version of this webpagePDF What this handout is about Friendly Version of this pagePrint Get a PDF", R.mipmap.user));
       // commentList.add(new CommentItem("3", "Samual Dahir", "18 days ago", "Print Friendly Version of this pagePrint Get a PDF version of this webpagePDF What this handout is about Friendly Version of this pagePrint Get a PDF", R.mipmap.user));
       // commentList.add(new CommentItem("4", "Samual Dahir", "18 days ago", "Print Friendly Version of this pagePrint Get a PDF version of this webpagePDF What this handout is about Friendly Version of this pagePrint Get a PDF", R.mipmap.user));
        //commentList.add(new CommentItem("5", "Samual Dahir", "18 days ago", "Print Friendly Version of this pagePrint Get a PDF version of this webpagePDF What this handout is about Friendly Version of this pagePrint Get a PDF", R.mipmap.user));
        //commentList.add(new CommentItem("6", "Samual Dahir", "18 days ago", "Print Friendly Version of this pagePrint Get a PDF version of this webpagePDF What this handout is about Friendly Version of this pagePrint Get a PDF", R.mipmap.user));

        commentadapter = new CommentAdapter(getActivity(), commentList);

        commentRecycler.setItemAnimator(new DefaultItemAnimator());
        commentRecycler.setAdapter(commentadapter);
        sliderData();
        startIntroAnimation();

        return view;
    }

    private void sliderData() {
        //HashMap<String,String> url_maps = new HashMap<>();
        // url_maps.put("", "http://www.pachd.com/free-images/food-images/oranges-01.jpg");
        // url_maps.put("", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        // url_maps.put("", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        //  url_maps.put("", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String, Integer> file_maps = new HashMap<>();
        file_maps.put("", R.drawable.cc);
        file_maps.put("", R.drawable.bb);
        file_maps.put("", R.drawable.hh);
        file_maps.put("", R.drawable.rr);

        for (String name : file_maps.keySet()) {
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
                    .putString("extra", name);

            imageSlider.addSlider(textSliderView);
        }
        imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(2000);

    }


    private void startIntroAnimation() {
      //  ViewCompat.setElevation(getToolbar(), 0);
        scrollviewComments.setScaleY(0.1f);
       //scrollviewComments.setPivotY(2);
        scrollviewComments.setTranslationY(200);

        scrollviewComments.animate()
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //ViewCompat.setElevation(getToolbar(), Utils.dpToPx(8));
                      //  animateContent();
                    }
                })
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
                //MainFragment.callBack(3);
                break;
        }
    }
}