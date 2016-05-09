package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by USER on 12/15/2015.
 */
public class FragmentCustomCalendar extends Fragment {
    CustomCalendarView calendarView;
    CalendarView calendar;
    TextView textView;
    String date_booking;
    int fid;
    User user;
    String userID;
    String location;
    int socketTimeout = 60000;
    String current_date;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_calendar, container, false);
        user = PrefUtils.getCurrentUser(getContext());
        textView = (TextView) rootView.findViewById(R.id.typeField);
        final String title = getArguments().getString("title");
        final String field = getArguments().getString("field");
        final int id = getArguments().getInt("ID");
        fid = id;
        final String typeField = getArguments().getString("catField");
        location = getArguments().getString("location");
        userID = getArguments().getString("userid");
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
                SimpleDateFormat df2 = new SimpleDateFormat("dd MMM yyyy");
                date_booking = df1.format(date);
                String strdate = df2.format(date);
                Fragment fragment = new FieldTime();
                Bundle bundle = new Bundle();
                bundle.putString("dateBooking", df.format(date));
                bundle.putString("dayBooking", id + "/" + df1.format(date));
                bundle.putString("title", title);
                bundle.putString("field", field);
                bundle.putInt("ID", id);
                bundle.putString("catField", typeField);
                bundle.putString("location", location);
                bundle.putString("dateselected", strdate);
                bundle.putString("uid", userID);
                bundle.putString("strdate", date_booking);
                bundle.putString("current_date",current_date);

                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.layoutCalendarBooking, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                saveDataBooking();
//                Toast.makeText(getActivity(), "id" + userID + "fid" + fid + "date" + strdate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date time) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
//                Toast.makeText(getActivity(), df.format(time), Toast.LENGTH_SHORT).show();
            }
        });

        JsonArrayRequest reqDate = new JsonArrayRequest(Constant.URL_DATE + date_booking, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        current_date = obj.getString("date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "date " + volleyError, Toast.LENGTH_SHORT).show();
            }
        });
        reqDate.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(reqDate);

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentSmall();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title);
                    bundle.putString("field", field);
                    bundle.putString("location", location);
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
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

    private class ColorDecorator implements DayDecorator {
        @Override
        public void decorate(DayView cell) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
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

    public void saveDataBooking() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_CHECKDATE + fid + "/" + date_booking , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                Toast.makeText(getActivity(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), "add date "+volleyError.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("calendar1", "" + volleyError);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("booking_date", date_booking);
                params.put("uid", userID);
                params.put("fid", String.valueOf(fid));
                return params;
            }
        };
        userReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(userReq);
    }
}
