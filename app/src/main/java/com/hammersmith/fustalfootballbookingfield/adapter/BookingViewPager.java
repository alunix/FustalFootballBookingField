package com.hammersmith.fustalfootballbookingfield.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;

/**
 * Created by Khmer on 10/2/2015.
 */
public class BookingViewPager extends FragmentPagerAdapter {

    public BookingViewPager(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        ActivityBooking.MyFragment fragment = ActivityBooking.MyFragment.newInstance(position);
        return fragment;
    }
    @Override
    public int getCount() {
        return 1;
    }
}
