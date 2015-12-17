package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Time;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/16/2015.
 */
public class FragmentTime extends Fragment {
    int i;

    TextView dayBooking;
    String titles[];
    String bookingDay;
    String catField;
    String fieldBooking;
    String dayBookingService;
    int ids;
    List<Time> times = new ArrayList<>();
    Time time;
    String timeBook;

    String timeBook6 = "6 To 7 AM";
    String timeBook7 = "7 To 8 AM";
    String timeBook8 = "8 To 9 AM";
    String timeBook9 = "9 To 10 AM";
    String timeBook10 = "10 To 11 AM";
    String timeBook11 = "11 To 12 AM";
    String timeBook12 = "12 To 1 PM";
    String timeBook13 = "1 To 2 PM";
    String timeBook14 = "2 To 3 PM";
    String timeBook15 = "3 To 4 PM";
    String timeBook16 = "4 To 5 PM";
    String timeBook17 = "5 To 6 PM";
    String timeBook18 = "6 To 7 PM";
    String timeBook19 = "7 To 8 PM";
    String timeBook20 = "8 To 9 PM";
    String timeBook21 = "9 To 10 PM";

    TextView time6, time7, time8, time9, time10, time11, time12, time13, time14, time15, time16, time17, time18, time19, time20, time21;
    TextView timeBooking6, timeBooking7, timeBooking8, timeBooking9, timeBooking10, timeBooking11, timeBooking12, timeBooking13, timeBooking14, timeBooking15, timeBooking16, timeBooking17, timeBooking18, timeBooking19, timeBooking20, timeBooking21;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        timeBooking6 = (TextView) rootView.findViewById(R.id.timeBooking6);
        timeBooking7 = (TextView) rootView.findViewById(R.id.timeBooking7);
        timeBooking8 = (TextView) rootView.findViewById(R.id.timeBooking8);
        timeBooking9 = (TextView) rootView.findViewById(R.id.timeBooking9);
        timeBooking10 = (TextView) rootView.findViewById(R.id.timeBooking10);
        timeBooking11 = (TextView) rootView.findViewById(R.id.timeBooking11);
        timeBooking12 = (TextView) rootView.findViewById(R.id.timeBooking12);
        timeBooking13 = (TextView) rootView.findViewById(R.id.timeBooking13);
        timeBooking14 = (TextView) rootView.findViewById(R.id.timeBooking14);
        timeBooking15 = (TextView) rootView.findViewById(R.id.timeBooking15);
        timeBooking16 = (TextView) rootView.findViewById(R.id.timeBookin16);
        timeBooking17 = (TextView) rootView.findViewById(R.id.timeBooking17);
        timeBooking18 = (TextView) rootView.findViewById(R.id.timeBooking18);
        timeBooking19 = (TextView) rootView.findViewById(R.id.timeBooking19);
        timeBooking20 = (TextView) rootView.findViewById(R.id.timeBooking20);
        timeBooking21 = (TextView) rootView.findViewById(R.id.timeBooking21);

        time6 = (TextView) rootView.findViewById(R.id.time6);
        time7 = (TextView) rootView.findViewById(R.id.time7);
        time8 = (TextView) rootView.findViewById(R.id.time8);
        time9 = (TextView) rootView.findViewById(R.id.time9);
        time10 = (TextView) rootView.findViewById(R.id.time10);
        time11 = (TextView) rootView.findViewById(R.id.time11);
        time12 = (TextView) rootView.findViewById(R.id.time12);
        time13 = (TextView) rootView.findViewById(R.id.time13);
        time14 = (TextView) rootView.findViewById(R.id.time14);
        time15 = (TextView) rootView.findViewById(R.id.time15);
        time16 = (TextView) rootView.findViewById(R.id.time16);
        time17 = (TextView) rootView.findViewById(R.id.time17);
        time18 = (TextView) rootView.findViewById(R.id.time18);
        time19 = (TextView) rootView.findViewById(R.id.time19);
        time20 = (TextView) rootView.findViewById(R.id.time20);
        time21 = (TextView) rootView.findViewById(R.id.time21);

        dayBooking = (TextView) rootView.findViewById(R.id.dayBooking);
        final String day = getArguments().getString("dateBooking");
        bookingDay = day;
        dayBooking.setText(day);
        final String dayBookToService = getArguments().getString("dayBooking");
        dayBookingService = dayBookToService;
        final String title = getArguments().getString("title");
        catField = title;
        final String field = getArguments().getString("field");
        fieldBooking = field;
        final int id = getArguments().getInt("ID");
        ids = id;
        if (times.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_BOOK_DETAIL + dayBookToService, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
//                    id = new int[jsonArray.length()];
                    titles = new String[jsonArray.length()];
                    for (i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            time = new Time();
                            time.setTime(obj.getString("time"));
                            timeBook = titles[i] = obj.getString("time");
                            times.add(time);

                            if (timeBooking6.getText().equals(timeBook)) {
                                time6.setText("Booked");
                                time6.setEnabled(false);
                                time6.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook6);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking7.getText().equals(timeBook)) {
                                time7.setText("Booked");
                                time7.setEnabled(false);
                                time7.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time7.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook7);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking8.getText().equals(timeBook)) {
                                time8.setText("Booked");
                                time8.setEnabled(false);
                                time8.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time8.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook8);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking9.getText().equals(timeBook)) {
                                time9.setText("Booked");
                                time9.setEnabled(false);
                                time9.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time9.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook9);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking10.getText().equals(timeBook)) {
                                time10.setText("Booked");
                                time10.setEnabled(false);
                                time10.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time10.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook10);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking11.getText().equals(timeBook)) {
                                time11.setText("Booked");
                                time11.setEnabled(false);
                                time11.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook11);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking12.getText().equals(timeBook)) {
                                time12.setText("Booked");
                                time12.setEnabled(false);
                                time12.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time12.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook12);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking13.getText().equals(timeBook)) {
                                time13.setText("Booked");
                                time13.setEnabled(false);
                                time13.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time13.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook13);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking14.getText().equals(timeBook)) {
                                time14.setText("Booked");
                                time14.setEnabled(false);
                                time14.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time14.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook14);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking15.getText().equals(timeBook)) {
                                time15.setText("Booked");
                                time15.setEnabled(false);
                                time15.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time15.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook15);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking16.getText().equals(timeBook)) {
                                time16.setText("Booked");
                                time16.setEnabled(false);
                                time16.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time16.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook16);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking17.getText().equals(timeBook)) {
                                time17.setText("Booked");
                                time17.setEnabled(false);
                                time17.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time17.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook17);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking18.getText().equals(timeBook)) {
                                time18.setText("Booked");
                                time18.setEnabled(false);
                                time18.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time18.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook18);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking19.getText().equals(timeBook)) {
                                time19.setText("Booked");
                                time19.setEnabled(false);
                                time19.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time19.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook19);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking20.getText().equals(timeBook)) {
                                time20.setText("Booked");
                                time20.setEnabled(false);
                                time20.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time20.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook20);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                            if (timeBooking21.getText().equals(timeBook)) {
                                time21.setText("Booked");
                                time21.setEnabled(false);
                                time21.setBackgroundColor(getResources().getColor(R.color.red));
                            } else {
                                time21.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Fragment fragment = new FragmentBooking();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("timeBooking", timeBook21);
                                        bundle.putString("date", bookingDay);
                                        bundle.putString("title", catField);
                                        bundle.putString("field", fieldBooking);
                                        bundle.putString("dayBooking", dayBookingService);
                                        bundle.putInt("ID", ids);
                                        fragment.setArguments(bundle);

                                        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                                        fragmentTransaction.addToBackStack(null);
                                        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
//                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
                }
            });
            AppController.getInstance().addToRequestQueue(fieldReq);
        }


        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentCustomCalendar();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title);
                    bundle.putString("field", field);
                    bundle.putInt("ID", ids);
                    bundle.putString("title", catField);
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.layoutTimeBooking, fragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        return rootView;
    }
}
