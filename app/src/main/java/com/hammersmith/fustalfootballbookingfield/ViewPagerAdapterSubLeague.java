package com.hammersmith.fustalfootballbookingfield;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapterSubLeague extends FragmentStatePagerAdapter {

    //CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    SparseArray<Fragment> registerFragmentLeague = new SparseArray<Fragment>();

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapterSubLeague(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            tab_fixture fixture = new tab_fixture();
            return fixture;
        }
        if (position == 1)           // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            tab_score score = new tab_score();
            return score;
        }
        if (position == 2) {
            tab_table table = new tab_table();
            return table;
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
        registerFragmentLeague.put(position, fragment);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        registerFragmentLeague.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisterFragmentFixture(int position) {
        return registerFragmentLeague.get(position);
    }
}