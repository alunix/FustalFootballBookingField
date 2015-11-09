package com.hammersmith.fustalfootballbookingfield.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 11/5/2015.
 */
public class CalendarBooking extends RootFragment {
    CalendarView calendar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_booking,container,false);
        calendar = (CalendarView)view.findViewById(R.id.calendarView1);
        inilializeCalendar();
        return view;
    }

    private void inilializeCalendar() {

        calendar.setShowWeekNumber(false);
        calendar.setFirstDayOfWeek(2);
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getActivity(),dayOfMonth+" / "+month+" / "+year,Toast.LENGTH_SHORT).show();
                Fragment fragment = new TimeBooking();
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutCalendarBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });



    }
}