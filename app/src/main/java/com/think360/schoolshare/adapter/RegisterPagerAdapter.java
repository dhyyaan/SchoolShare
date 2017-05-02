package com.think360.schoolshare.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.think360.schoolshare.fragment.signupprofile.AcademicInfoFragment;
import com.think360.schoolshare.fragment.signupprofile.ContactInfoFragment;
import com.think360.schoolshare.fragment.signupprofile.FacebookLoginFragment;
import com.think360.schoolshare.fragment.signupprofile.PersonalInfoFragment;
import com.think360.schoolshare.fragment.signupprofile.ProfessionalInfoFragment;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by think360user on 19/12/16.
 */
public class RegisterPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
  //  private FragmentManager mFragmentManager;
//   private Map<Integer, String> mFragmentTags;

    public RegisterPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
   //     mFragmentManager = fragmentManager;
     //  mFragmentTags = new HashMap<Integer, String>();
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
          //  case 0: // Fragment # 0 - This will Facebook Login Fragment
             //   return FacebookLoginFragment.newInstance();
               // return CommentFragment.newInstance();
            case 0: // Fragment # 1 - This will show Personal Info Fragment
                return PersonalInfoFragment.newInstance();
            case 1: // Fragment # 2 - This will show Contact Info Fragment
                return ContactInfoFragment.newInstance();
            case 2: // Fragment # 3 - This will show Academic Info Fragment
                return AcademicInfoFragment.newInstance();
            case 3: // Fragment # 4 - This will show Professional Info Fragment
                return ProfessionalInfoFragment.newInstance();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
 /* @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

   /* @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;
    }*/

   /*   @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            mFragmentTags.put(position, tag);
        }
        return object;
    }
   public Fragment getFragment(int position) {

        Fragment fragment = null;
       String tag = mFragmentTags.get(position);
       if (tag != null) {
            fragment = mFragmentManager.findFragmentByTag(tag);
       }
      return  fragment;
    }*/



}

