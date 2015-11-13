package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapter;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;
import com.hammersmith.fustalfootballbookingfield.widget.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;


public class MainActivity extends RootFragment {

    // Declaring Your View and Variables
    Context context;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayoutText tabs;
    CharSequence Titles[]={"Home","Map","League","Setting"};
    int Numboftabs =4;
    public MainActivity(){
    }

    public MainActivity(Context context){
        this.context = context;
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
       // TextView textView = (TextView) view.findViewById(R.id.textCustomTab);
        //textView.setTypeface(fontFace);
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