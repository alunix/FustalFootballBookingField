package com.hammersmith.fustalfootballbookingfield.preivew;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapterSub;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;

/**
 * Created by USER on 9/21/2015.
 */
public class SubTab extends ActionBarActivity {
    ViewPager pager;
    ViewPagerAdapterSub adapter;
    SlidingTabLayoutText tabs;
    CharSequence Titles[] = {"FIXTURE","SCORE","TABLE"};
    int Numoftabs = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.league_fixture);
        adapter = new ViewPagerAdapterSub(getSupportFragmentManager(),Titles,Numoftabs);
        pager = (ViewPager)findViewById(R.id.sub_pager);
        pager.setAdapter(adapter);
        tabs = (SlidingTabLayoutText)findViewById(R.id.sub_tabs);
        tabs.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer(){

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(pager);
    }
}
