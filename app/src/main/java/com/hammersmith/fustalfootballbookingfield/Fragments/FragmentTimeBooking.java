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
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;

/**
 * Created by USER on 11/5/2015.
 */
public class FragmentTimeBooking extends RootFragment {
//    CharSequence[] time =  {"Time 6 To 7 AM",
//                            "Time 7 To 8 AM",
//                            "Time 8 To 9 AM",
//                            "Time 9 To 10 AM"};
//    int count = time.length;

    TextView time6_7am,time7_8am,time8_9am,time9_10am,time10_11am,time11_12pm,time12_1pm,time1_2pm,time2_3pm
             ,time3_4pm,time4_5pm,time5_6pm,time6_7pm,time7_8pm,time8_9pm,time9_10pm;
    TextView dayBooking;
    String time6to7AM = "Time 6 To 7 AM";
    String time7to8AM = "Time 7 To 8 AM";
    String time8to9AM = "Time 8 To 9 AM";
    String time9to10AM = "Time 9 To 10 AM";
    String time10to11AM = "Time 10 To 11 AM";
    String time11to12PM = "Time 11 To 12 PM";
    String time12to1PM = "Time 12 To 1 PM";
    String time1to2PM = "Time 1 To 2 PM";
    String time2to3PM = "Time 2 To 3 PM";
    String time3to4PM = "Time 3 To 4 PM";
    String time4to5PM = "Time 4 To 5 PM";
    String time5to6PM = "Time 5 To 6 PM";
    String time6to7PM = "Time 6 To 7 PM";
    String time7to8PM = "Time 7 To 8 PM";
    String time8to9PM = "Time 8 To 9 PM";
    String time9to10PM = "Time 9 To 10 PM";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_booking, container, false);
        dayBooking = (TextView) view.findViewById(R.id.timeBooking);
        time6_7am = (TextView) view.findViewById(R.id.time6to7am);
        time6_7am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This time not available for booking. Please choose other time!",Toast.LENGTH_SHORT).show();
            }
        });
        final String field = getArguments().getString("field");
        final String date = getArguments().getString("dateBooking");
        dayBooking.setText(date);
        time7_8am = (TextView) view.findViewById(R.id.time7to8am);
        time7_8am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time7to8AM);
                bundle.putString("date", date);
                bundle.putString("field", field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time8_9am = (TextView) view.findViewById(R.id.time8to9am);
        time8_9am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time8to9AM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time9_10am = (TextView) view.findViewById(R.id.time9to10am);
        time9_10am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This time not available for booking. Please choose other time!",Toast.LENGTH_SHORT).show();
            }
        });

        time10_11am = (TextView) view.findViewById(R.id.time10to11am);
        time10_11am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time10to11AM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time11_12pm = (TextView) view.findViewById(R.id.time11to12pm);
        time11_12pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time11to12PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time12_1pm = (TextView) view.findViewById(R.id.time12to1pm);
        time12_1pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This time not available for booking. Please choose other time!",Toast.LENGTH_SHORT).show();
            }
        });

        time1_2pm = (TextView) view.findViewById(R.id.time1to2pm);
        time1_2pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time1to2PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time2_3pm = (TextView) view.findViewById(R.id.time2to3pm);
        time2_3pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time2to3PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time3_4pm = (TextView) view.findViewById(R.id.time3to4pm);
        time3_4pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This time not available for booking. Please choose other time!",Toast.LENGTH_SHORT).show();
            }
        });

        time4_5pm = (TextView) view.findViewById(R.id.time4to5pm);
        time4_5pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time4to5PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time5_6pm = (TextView) view.findViewById(R.id.time5to6pm);
        time5_6pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time5to6PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time6_7pm = (TextView) view.findViewById(R.id.time6to7pm);
        time6_7pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This time not available for booking. Please choose other time!",Toast.LENGTH_SHORT).show();
            }
        });

        time7_8pm = (TextView) view.findViewById(R.id.time7to8pm);
        time7_8pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time7to8PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time8_9pm = (TextView) view.findViewById(R.id.time8to9pm);
        time8_9pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentBooking();

                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", time8to9PM);
                bundle.putString("date", date);
                bundle.putString("field",field);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutTimeBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        time9_10pm = (TextView) view.findViewById(R.id.time9to10pm);
        time9_10pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This time not available for booking. Please choose other time!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
