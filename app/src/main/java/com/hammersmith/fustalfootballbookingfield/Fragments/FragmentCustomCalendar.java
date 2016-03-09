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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    String strdate;
    int fid;
    User user;
    String userid;
    String location;

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
        calendarView = (CustomCalendarView) rootView.findViewById(R.id.calendar_view);
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());



        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    userid = jsonObject.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);

        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setShowOverflowDate(false);
        calendarView.refreshCalendar(currentCalendar);
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat df2 = new SimpleDateFormat("dd MMM yyyy");
                strdate = df1.format(date);
                String strdate = df2.format(date);
//                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance());
//                if (strdate.compareTo(mydate)>0){
//                    Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getActivity(), df2.format(date), Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getActivity(),strdate,Toast.LENGTH_SHORT).show();
                Fragment fragment = new FragmentTime();
                Bundle bundle = new Bundle();
                bundle.putString("dateBooking", df.format(date));
                bundle.putString("dayBooking", id + "/" + df1.format(date));
                bundle.putString("title", title);
                bundle.putString("field", field);
                bundle.putInt("ID", id);
                bundle.putString("catField", typeField);
                bundle.putString("location",location);
                bundle.putString("dateselected",strdate);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.layoutCalendarBooking, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                saveDataBooking();
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
                    bundle.putString("title", title);
                    bundle.putString("field", field);
                    bundle.putString("location",location);
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
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_CHECKDATE + fid + "/" + strdate + "/new", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

//                Toast.makeText(getActivity(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("booking_date", strdate);
                params.put("uid", userid);
                params.put("fid", String.valueOf(fid));
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
    }
}
