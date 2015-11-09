package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.preivew.Booking;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;
import com.hammersmith.fustalfootballbookingfield.users.TabBooking;

/**
 * Created by USER on 11/9/2015.
 */
public class TabAllField extends RootFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_list,container,false);

        Fragment fragment = new TabBooking();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutList,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        return view;
    }
}
