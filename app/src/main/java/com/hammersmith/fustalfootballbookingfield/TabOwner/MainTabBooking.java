package com.hammersmith.fustalfootballbookingfield.TabOwner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.widget.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapterOwner;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;

/**
 * Created by USER on 9/28/2015.
 */
public class MainTabBooking extends RootFragment {

    ViewPager pager;
    ViewPagerAdapterOwner adapter;
    SlidingTabLayoutText tab;
    CharSequence Titles[] = {"Confirm","Setting"};
    int NumbOfTabs = 2;
    public MainTabBooking(){

    }

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_booking,container,false);

        pager = (ViewPager)view.findViewById(R.id.sub_pager);
        tab = (SlidingTabLayoutText)view.findViewById(R.id.sub_tabs);
        tab.setCustomTabView(R.layout.custom_tab_sub, R.id.textTabs);
        tab.setDistributeEvenly(true);
        tab.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabColorTest);
            }
        });


        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //adapter =  new ViewPagerAdapterSubLeague(getActivity().getSupportFragmentManager(),Titles,NumbOfTabs);
        adapter = new ViewPagerAdapterOwner(getChildFragmentManager(),Titles,NumbOfTabs);
        pager.setAdapter(adapter);
        tab.setViewPager(pager);
    }

    @Override
    public boolean onBackPress() {
        OnBackPressListener currentFragment = (OnBackPressListener)adapter.getRegisterFragmentField(pager.getCurrentItem());
        if(currentFragment != null){
            return currentFragment.onBackPress();
        }
        return false;
    }
}
