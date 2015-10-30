package com.hammersmith.fustalfootballbookingfield.preivew;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapterSubLeague;
import com.hammersmith.fustalfootballbookingfield.users.RootFragmgmet;
import com.hammersmith.fustalfootballbookingfield.widget.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;

/**
 * Created by USER on 9/18/2015.
 */
public class League_Fixture extends RootFragmgmet {


    ViewPager pager;
    ViewPagerAdapterSubLeague adapter;
    SlidingTabLayoutText tab;
    CharSequence Titles[] = {"Fixture","Score","Table"};
    int NumbOfTabs = 3;


    public League_Fixture(){

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.league_fixture, container, false);

        pager = (ViewPager) view.findViewById(R.id.sub_pager);
        tab = (SlidingTabLayoutText) view.findViewById(R.id.sub_tabs);
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
        adapter = new ViewPagerAdapterSubLeague(getChildFragmentManager(),Titles,NumbOfTabs);
        pager.setAdapter(adapter);
        tab.setViewPager(pager);
    }

    @Override
    public boolean onBackPress() {
        OnBackPressListener currentFragment = (OnBackPressListener)adapter.getRegisterFragmentFixture(pager.getCurrentItem());
        if(currentFragment != null){
            return currentFragment.onBackPress();
        }
        return false;
    }
}
