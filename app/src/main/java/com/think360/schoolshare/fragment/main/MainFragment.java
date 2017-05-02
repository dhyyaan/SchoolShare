package com.think360.schoolshare.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.think360.schoolshare.R;

import com.think360.schoolshare.activity.MainActivitySurinder;
import com.think360.schoolshare.fragment.StoreFragment.StoreFragment;
import com.think360.schoolshare.fragment.editprofile.ContactInfoEditFragment;
import com.think360.schoolshare.fragment.editprofile.AcademicInfoEditFragment;
import com.think360.schoolshare.fragment.editprofile.AcademicInfoEditFragmentSubmit;
import com.think360.schoolshare.fragment.editprofile.ContactInfoEditFragmentSubmit;
import com.think360.schoolshare.fragment.editprofile.EditProfileFragment;
import com.think360.schoolshare.fragment.editprofile.ProfessionalInfoEditFragmentSubmit;
import com.think360.schoolshare.fragment.newsandevents.CommentFragment;
import com.think360.schoolshare.fragment.newsandevents.EventsFragment;
import com.think360.schoolshare.fragment.newsandevents.NewsAndEventFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragment;
import com.think360.schoolshare.fragment.editprofile.PersonalInfoEditFragmentSubmit;
import com.think360.schoolshare.fragment.editprofile.ProfessionalInfoEditFragment;
import com.think360.schoolshare.fragment.newsandevents.NewsFragment;
import com.think360.schoolshare.fragment.settings.SettingsFragments;
import com.think360.schoolshare.fragment.menu.MenuFragment;
import com.think360.schoolshare.fragment.members.MembersFragment;
import com.think360.schoolshare.interfaces.OnFragmentInteractionListener;
import com.think360.schoolshare.surinder.utils.NonSwipeableViewPager;
import com.think360.schoolshare.surinder.utils.OnBackPressListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String[] mTitles = {"Projects", "Applied", "Live", "Completed"};
    private static NonSwipeableViewPager mViewPager;
    private MyPagerAdapter myPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(int param1,String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public boolean onBackPressed() {
        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) myPagerAdapter.getRegisteredFragment(mViewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mFragments.add(MembersFragment.newInstance("MembersFragment", "MembersFragment"));
        mFragments.add(StoreFragment.newInstance("MembersFragment", "MembersFragment"));
        mFragments.add(NewsAndEventFragment.newInstance("NewsAndEventFragment", "NewsAndEventFragment"));
        mFragments.add(MenuFragment.newInstance("MenuFragment", "MenuFragment"));

        mViewPager = (NonSwipeableViewPager) rootView.findViewById(R.id.viewPager);
        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        callBack(mParam1);
        return rootView;
    }

    public static void callBack(int i) {
        switch (i) {

           case 0:
                //member fragment
                mViewPager.setCurrentItem(0);
               if (MainActivitySurinder.bottomButtonMembers.getChildAt(1) instanceof TextView) {
                   ((ImageView)MainActivitySurinder. bottomButtonMembers.getChildAt(0)).setColorFilter(Color.parseColor("#FF5253"));
                    ((TextView) MainActivitySurinder.bottomButtonMembers.getChildAt(1)).setTextColor(Color.parseColor("#FF5253"));
                }
                break;

            case 1:
                //member fragment
                mViewPager.setCurrentItem(1);

                break;
            case 2:
                //news and event fragment
                mViewPager.setCurrentItem(2);
                if (MainActivitySurinder.bottomButtonNewsAndEvents.getChildAt(1) instanceof TextView) {
                    ((ImageView)MainActivitySurinder. bottomButtonNewsAndEvents.getChildAt(0)).setColorFilter(Color.parseColor("#FF5253"));
                    ((TextView) MainActivitySurinder.bottomButtonNewsAndEvents.getChildAt(1)).setTextColor(Color.parseColor("#FF5253"));
                }
                break;
            case 3:
                //menu fragment
                mViewPager.setCurrentItem(3);

                break;
            case 4:
                //settings fragment
                mViewPager.setCurrentItem(4);

                break;
            case 5:
                //edit profile info fragment
                mViewPager.setCurrentItem(5);

                break;
            case 6:
                //perosonal Info Edit Fragment  calling
                mViewPager.setCurrentItem(6);

                break;
            case 7:
       //contact Info Edit Fragment  calling
                mViewPager.setCurrentItem(7);

                break;
            case 8:
                //academic Info Edit Fragment  calling
                mViewPager.setCurrentItem(8);

                break;
            case 9:
                //professional Info Edit Fragment  calling
                mViewPager.setCurrentItem(9);

                break;

        }

    }








    private class MyPagerAdapter extends FragmentPagerAdapter {
        SparseArray<Fragment> registeredFragments = new SparseArray<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        /**
         * Get the Fragment by position
         *
         * @param position tab position of the fragment
         * @return
         */
        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        /**
         * On each Fragment instantiation we are saving the reference of that Fragment in a Map
         * It will help us to retrieve the Fragment by position
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        /**
         * Remove the saved reference from our Map on the Fragment destroy
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);


        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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


}
