package com.hammersmith.fustalfootballbookingfield.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hammersmith.fustalfootballbookingfield.Activities.ActivityUpdate;
import com.hammersmith.fustalfootballbookingfield.Activities.ViewProfile;

/**
 * Created by Thuon on 2/11/2016.
 */
public class ViewPagerUpdate extends FragmentPagerAdapter {
    public ViewPagerUpdate(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ActivityUpdate.MyFragment fragment = ActivityUpdate.MyFragment.newInstance(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
