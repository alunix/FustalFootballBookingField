package com.hammersmith.fustalfootballbookingfield.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hammersmith.fustalfootballbookingfield.Activities.ViewProfile;

/**
 * Created by Thuon on 2/11/2016.
 */
public class ViewPagerProfile extends FragmentPagerAdapter {
    public ViewPagerProfile(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ViewProfile.MyFragment fragment = ViewProfile.MyFragment.newInstance(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
