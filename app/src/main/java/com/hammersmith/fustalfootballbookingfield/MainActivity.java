package com.hammersmith.fustalfootballbookingfield;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends RootFragmgmet {

    // Declaring Your View and Variables
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayoutText tabs;
    CharSequence Titles[]={"List","Map","League"};
    int Numboftabs =3;
    public MainActivity(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);

        pager = (ViewPager)view.findViewById(R.id.pager);
        tabs = (SlidingTabLayoutText)view.findViewById(R.id.tabs);
        tabs.setTabBackgroundColor(getResources().getColor(R.color.selectors));
        //tabs.setSelectedIndicatorColors(getResources().getColor(R.color.selectors));
        tabs.setCustomTabView(R.layout.custom_tab_text, R.id.textCustomTab);
        tabs.setDistributeEvenly(true);


        tabs.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabColorTest);
            }
        });
        return view;
    }
    @Override
    public void onDestroy()
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //adapter =  new ViewPagerAdapterSubLeague(getActivity().getSupportFragmentManager(),Titles,NumbOfTabs);
        adapter = new ViewPagerAdapter(getChildFragmentManager(),Titles,Numboftabs);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }



    public boolean onBackPress() {
        OnBackPressListener currentFragment = (OnBackPressListener)adapter.getRegisterFragment(pager.getCurrentItem());
        if(currentFragment != null){
            return currentFragment.onBackPress();
        }
        return false;
    }
}