package com.hammersmith.fustalfootballbookingfield.Fragments;

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
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;

/**
 * Created by USER on 11/5/2015.
 */
public class FragmentTimeBooking extends RootFragment {
    String dateBooking;
    TextView textView;
    TextView booking;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_booking, container, false);
        final String date = getArguments().getString("dateBooking");
        booking = (TextView) view.findViewById(R.id.txtBooking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking","9 To 10 AM");
                bundle.putString("date",date);
                fragment.setArguments(bundle);
//                bundle.putString("dateBooking","12/10/2015");
//                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
