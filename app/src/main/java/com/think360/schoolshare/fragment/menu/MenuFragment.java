package com.think360.schoolshare.fragment.menu;

/**
 * Created by think360user on 7/2/17.
 */

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
import android.widget.RelativeLayout;


import com.balysv.materialripple.MaterialRippleLayout;
import com.think360.schoolshare.R;
import com.think360.schoolshare.fragment.calendar.CompactCalendarTab;
import com.think360.schoolshare.fragment.circular.CircularListFragment;
import com.think360.schoolshare.fragment.editprofile.EditProfileFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragmentSubmit;
import com.think360.schoolshare.fragment.newsandevents.NewsAndEventFragment;
import com.think360.schoolshare.fragment.main.MainFragment;
import com.think360.schoolshare.fragment.notification.NotificationListFragment;
import com.think360.schoolshare.fragment.settings.SettingsFragments;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.surinder.utils.RootFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsAndEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends RootFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
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
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FrameLayout iv_settings = (FrameLayout) view.findViewById(R.id.settings);
        iv_settings.setOnClickListener(this);

        FrameLayout edit_profile = (FrameLayout) view.findViewById(R.id.edit);
        edit_profile.setOnClickListener(this);

        RelativeLayout rippleCircular = (RelativeLayout) view.findViewById(R.id.rl_circular);
        rippleCircular.setOnClickListener(this);
        RelativeLayout rl_calender = (RelativeLayout) view.findViewById(R.id.rl_calender);
        rl_calender.setOnClickListener(this);
        RelativeLayout rl_dashboard = (RelativeLayout) view.findViewById(R.id.rl_dashboard);
        rl_dashboard.setOnClickListener(this);

        RelativeLayout rl_notification = (RelativeLayout) view.findViewById(R.id.rl_notification);
        rl_notification.setOnClickListener(this);




    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
     /*   if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // Store the Fragment in stack
        transaction.addToBackStack(null);
        switch (view.getId()) {

            case R.id.rl_dashboard:
                getActivity().finish();
                break;
            case R.id.settings:
                //  FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                // Store the Fragment in stack
                //  transaction.addToBackStack(null);
                transaction.replace(R.id.fragContainer, SettingsFragments.newInstance("" + "", "")).commitAllowingStateLoss();
                //  MainFragment.callBack(4);
                break;

            case R.id.rl_notification:
                transaction.replace(R.id.fragContainer, NotificationListFragment.newInstance("" + "", "")).commitAllowingStateLoss();

                break;
            case R.id.edit:

                transaction.replace(R.id.fragContainer, EditProfileFragment.newInstance("" + "", "")).commitAllowingStateLoss();
                //MainFragment.callBack(5);
                break;

            case R.id.rl_circular:
                transactFragment(R.id.fragContainer, CircularListFragment.newInstance("", ""));
                break;

            case R.id.rl_calender:
                transactFragment(R.id.fragContainer, CompactCalendarTab.newInstance("",""));
                break;
        }

    }

}
