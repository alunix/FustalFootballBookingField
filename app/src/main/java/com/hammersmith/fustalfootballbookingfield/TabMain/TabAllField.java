package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabBooking.MainTabBooking;
import com.hammersmith.fustalfootballbookingfield.TabBooking.TabChecking;
import com.hammersmith.fustalfootballbookingfield.preivew.Booking;
import com.hammersmith.fustalfootballbookingfield.users.CheckingUser;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;
import com.hammersmith.fustalfootballbookingfield.users.TabBooking;

/**
 * Created by USER on 11/9/2015.
 */
public class TabAllField extends RootFragment {
    TextView booking;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_list, container, false);

        booking = (TextView) view.findViewById(R.id.bookingfield);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CheckingUser();
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutList, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
