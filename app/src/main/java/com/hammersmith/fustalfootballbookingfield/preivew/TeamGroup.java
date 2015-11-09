package com.hammersmith.fustalfootballbookingfield.preivew;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapterSub;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;
import com.hammersmith.fustalfootballbookingfield.widget.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;

/**
 * Created by USER on 9/17/2015.
 */
public class TeamGroup extends RootFragment {
    ViewPager pager;
    ViewPagerAdapterSub adapter;
    SlidingTabLayoutText tabs;
    CharSequence Titles[]={"Post","Team","About"};
    int NumofTabs = 3;
    public TeamGroup(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.team_group, container, false);
//        TextView teamName = (TextView)v.findViewById(R.id.textTeamName);
//        teamName.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
//        teamName.setTextSize(18);


        pager = (ViewPager)v.findViewById(R.id.sub_pager);

        tabs = (SlidingTabLayoutText)v.findViewById(R.id.sub_tabs);
        tabs.setCustomTabView(R.layout.sub_custom_tab, R.id.textTabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.subTabsScrollColor);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //adapter = new ViewPagerAdapterSub(getActivity().getSupportFragmentManager(),Titles,NumofTabs);
        adapter = new ViewPagerAdapterSub(getChildFragmentManager(),Titles,NumofTabs);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }


    public boolean onBackPress() {
        OnBackPressListener currentFragment = (OnBackPressListener)adapter.getRegisterFragmentsub(pager.getCurrentItem());
        if(currentFragment != null){
            return currentFragment.onBackPress();
        }
        return false;
    }
}
