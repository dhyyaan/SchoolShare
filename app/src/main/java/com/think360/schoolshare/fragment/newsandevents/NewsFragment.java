package com.think360.schoolshare.fragment.newsandevents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.think360.schoolshare.R;
import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.fragment.settings.SettingsFragments;
import com.think360.schoolshare.surinder.utils.RootFragment;

import java.util.HashMap;

/**
 * Created by think360user on 11/2/17.
 */

public class NewsFragment extends RootFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SliderLayout imageSlider;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsAndEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        // Bundle args = new Bundle();
        //  args.putString(ARG_PARAM1, param1);
        ////  args.putString(ARG_PARAM2, param2);
        //   fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout fl = (FrameLayout) view.findViewById(R.id.back);
        fl.setOnClickListener(this);
        imageSlider = (SliderLayout)view.findViewById(R.id.slider);
        LinearLayout comments = (LinearLayout)view.findViewById(R.id.linearLayout6);
        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
// Store the Fragment in stack
                transaction.addToBackStack(null);
               // transaction.replace(R.id.fragContainer, CommentFragment.newInstance("" + "", "")).commitAllowingStateLoss();

                //  MainFragment.callBack(14);
            }
        });
        // FrameLayout fl_back = (FrameLayout) view.findViewById(R.id.back);
        // fl_back.setOnClickListener(this);
        //  StickyListHeadersListView stickyList = (StickyListHeadersListView) view.findViewById(R.id.list);
        //  MyAdapter adapter = new MyAdapter(getContext());
        //   stickyList.setAdapter(adapter);
        sliderData();
    }
    private void  sliderData(){
        //HashMap<String,String> url_maps = new HashMap<>();
       // url_maps.put("", "https://pixabay.com/en/children-different-drawings-faces-1853193/");
      //  url_maps.put("", "https://www.shutterstock.com/image-photo/happy-kids-taking-selfie-school-corridor-309278672");
       // url_maps.put("", "https://pixabay.com/en/school-children-happy-smile-joy-1443779/");
        //url_maps.put("", "https://pixabay.com/en/school-children-happy-food-smile-1443782/");

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

  /*  public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        private String[] countries;
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            countries = context.getResources().getStringArray(R.array.countries);
        }

        @Override
        public int getCount() {
            return countries.length;
        }

        @Override
        public Object getItem(int position) {
            return countries[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.menu_fragment_item, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(countries[position]);

            return convertView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            //set header text as first char in name
            String headerText = "" + countries[position].subSequence(0, 1).charAt(0);
            holder.text.setText(headerText);
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            //return the first character of the country as ID because this is what headers are based upon
            return countries[position].subSequence(0, 1).charAt(0);
        }

        class HeaderViewHolder {
            TextView text;
        }

        class ViewHolder {
            TextView text;
        }

    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        //MainActivitySurinder.self.onBackPressed();

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

  @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                MainActivitySurinder.self.onBackPressed();
               // MainFragment.callBack(3);
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
  /*  public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
