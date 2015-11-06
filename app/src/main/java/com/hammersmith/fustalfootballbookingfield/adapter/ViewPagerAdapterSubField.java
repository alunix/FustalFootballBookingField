package com.hammersmith.fustalfootballbookingfield.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.TabBooking.TabSetting;
import com.hammersmith.fustalfootballbookingfield.TabBooking.TabChecking;
import com.hammersmith.fustalfootballbookingfield.TabBooking.TabConfirm;

/**
 * Created by USER on 9/28/2015.
 */
public class ViewPagerAdapterSubField extends FragmentStatePagerAdapter {

    //CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    SparseArray<Fragment> registerFragmentFiled = new SparseArray<Fragment>();

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterSubField(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            TabConfirm confirm = new TabConfirm();
            return confirm;
        }
        if(position == 1){
            TabSetting setting = new TabSetting();
            return setting;
        }
        return null;
    }



// This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

// This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container,position);
        registerFragmentFiled.put(position, fragment);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        registerFragmentFiled.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisterFragmentField(int position) {
        return registerFragmentFiled.get(position);
    }

}
