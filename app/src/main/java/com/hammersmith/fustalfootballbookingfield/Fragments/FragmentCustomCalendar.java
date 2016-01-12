package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by USER on 12/15/2015.
 */
public class FragmentCustomCalendar extends Fragment{
    CustomCalendarView calendarView;
    CalendarView calendar;
    String catField, fields;
    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_calendar, container, false);

        textView = (TextView) rootView.findViewById(R.id.typeField);
        final String title = getArguments().getString("title");
        catField = title;
        final String field = getArguments().getString("field");
        final int id = getArguments().getInt("ID");
        fields = field;

        calendarView = (CustomCalendarView) rootView.findViewById(R.id.calendar_view);
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);
        calendarView.refreshCalendar(currentCalendar);
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

                Fragment fragment = new FragmentTime();

                Bundle bundle = new Bundle();
                bundle.putString("dateBooking", df.format(date));
                bundle.putString("dayBooking", id + "/" + df1.format(date));
                bundle.putString("title", catField);
                bundle.putString("field", fields);
                bundle.putInt("ID", id);

                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.layoutCalendarBooking, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onMonthChanged(Date time) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
//                Toast.makeText(getActivity(), df.format(time), Toast.LENGTH_SHORT).show();
            }
        });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentSmall();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", catField);
                    bundle.putString("field", fields);
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                    transaction.replace(R.id.layoutCalendarBooking, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        return rootView;
    }
    private class ColorDecorator implements DayDecorator{
        @Override
        public void decorate(DayView cell) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
            cell.setBackgroundColor(color);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
