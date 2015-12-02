package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 11/5/2015.
 */
public class FragmentCalendarBooking extends Fragment {
    CalendarView calendar;
    String str;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_booking, container, false);
        textView = (TextView) view.findViewById(R.id.typeField);
        String field = getArguments().getString("field");
        str = field;
//        textView.setText(field);

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
                //Toast.makeText(getActivity(),dayOfMonth+" / "+month+" / "+year,Toast.LENGTH_SHORT).show();
                month = month+1;
                Fragment fragment = new FragmentTimeBooking();

                Bundle bundle = new Bundle();
                bundle.putString("dateBooking",dayOfMonth+"/"+month+"/"+year);
                bundle.putString("field",str);
                fragment.setArguments(bundle);
//                bundle.putString("field", str);
//                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutCalendarBooking,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });



    }
}
