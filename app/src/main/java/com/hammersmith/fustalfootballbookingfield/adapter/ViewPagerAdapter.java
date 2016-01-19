package com.hammersmith.fustalfootballbookingfield.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.TabMain.Map;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabHome;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabLeague;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabMap;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    //int iconTab[];
    //int iconSelected[];
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    SparseArray<Fragment> registerFragment = new SparseArray<Fragment>();


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        //this.iconTab = mIconTab;
        //this.iconSelected = mIconSelected;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabHome();
            case 1:
                return new Map();
            case 2:
                return new TabLeague();
            default:
                return null;
        }


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


    public Fragment getRegisterFragment(int position) {
        return registerFragment.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registerFragment.put(position, fragment);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        registerFragment.remove(position);
        super.destroyItem(container, position, object);
    }
    //@Override
//    public int getPageIconSelected(int position) {
//        return iconSelected[position];
//    }
}