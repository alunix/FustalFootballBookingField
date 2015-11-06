package com.hammersmith.fustalfootballbookingfield.TabBooking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.widget.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapterSubField;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;

/**
 * Created by USER on 9/28/2015.
 */
public class MainTabBooking extends RootFragment {

    ViewPager pager;
    ViewPagerAdapterSubField adapter;
    SlidingTabLayoutText tab;
    CharSequence Titles[] = {"Booking","Setting"};
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
        View view = inflater.inflate(R.layout.field_owner,container,false);

        pager = (ViewPager)view.findViewById(R.id.sub_pager);
        tab = (SlidingTabLayoutText)view.findViewById(R.id.sub_tabs);
        tab.setCustomTabView(R.layout.sub_custom_tab, R.id.textTabs);
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
        adapter = new ViewPagerAdapterSubField(getChildFragmentManager(),Titles,NumbOfTabs);
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
