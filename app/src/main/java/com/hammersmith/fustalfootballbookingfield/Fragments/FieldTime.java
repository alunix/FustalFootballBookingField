package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterLeague;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterTime;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.League;
import com.hammersmith.fustalfootballbookingfield.model.Time;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Thuon on 4/25/2016.
 */
public class FieldTime extends Fragment implements AdapterTime.ClickListener {
    RecyclerView recyclerView;
    AdapterTime adapterTime;
    List<Time> times = new ArrayList<>();
    ArrayList<String> timeBooks = new ArrayList<>();
    ArrayList<String> showTimeBooks = new ArrayList<>();
    Time time;
    private ProgressDialog pDialog;
    User user;
    String catFieldDetail;
    String dateselected;
    TextView dayBooking;
    TextView available;
    String get_time[];
    String get_time_book[];
    String bookingDay;
    String catField;
    String dayBookingService;
    String field;
    String userID;
    String strdate;
    int ids;
    String str;
    String get_text;
    String location;
    String current_date;
    int socketTimeout = 60000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_time, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterTime = new AdapterTime(getActivity(), times);
        recyclerView.setAdapter(adapterTime);
        recyclerView.setHasFixedSize(true);
        adapterTime.setClickListener(this);
        final String dayBookToService = getArguments().getString("dayBooking");
        user = PrefUtils.getCurrentUser(getContext());
        available = (TextView) rootView.findViewById(R.id.available);
        dayBooking = (TextView) rootView.findViewById(R.id.dayBooking);
        final String day = getArguments().getString("dateBooking");
        bookingDay = day;
        dayBooking.setText(day);
        dayBookingService = dayBookToService;
        final String title = getArguments().getString("title");
        catField = title;
        field = getArguments().getString("field");
        final int id = getArguments().getInt("ID");
        ids = id;
        catFieldDetail = getArguments().getString("catField");
        strdate = getArguments().getString("strdate");
        location = getArguments().getString("location");
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        dateselected = getArguments().getString("dateselected");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        showPDialog();

        JsonArrayRequest reqDate = new JsonArrayRequest(Constant.URL_DATE + strdate, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        current_date = obj.getString("date");
                        hidePDialog();
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

        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    userID = jsonObject.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "get user id " + volleyError, Toast.LENGTH_SHORT).show();
            }
        });
        objectRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(objectRequest);

        JsonArrayRequest req = new JsonArrayRequest(Constant.URL_BOOK_DETAIL + dayBookToService, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        timeBooks.add(obj.getString("time_booked"));
                        str = obj.getString("time_booked");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "time " + volleyError, Toast.LENGTH_SHORT).show();
            }
        });
        req.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(req);

        if (times.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest request = new JsonArrayRequest(Constant.URL_TIME + id, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    get_time = new String[jsonArray.length()];
                    get_time_book = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            time = new Time();
                            time.setTime(obj.getString("time"));
                            showTimeBooks.add(obj.getString("time"));
                            if (current_date.equals("invalid")) {
                                time.setBook("Booked");
                            }
                            if((current_date.equals("valid"))) {
                                for (int j = 0; j < timeBooks.size(); j++) {
                                    if (str.equals("invalid_date")) {
                                        time.setBook("Available Booking");
                                        break;
                                    } else {
                                        if (showTimeBooks.get(i).equals(timeBooks.get(j))) {
                                            time.setBook("Booked");
                                            break;
                                        } else {
                                            time.setBook("Available Booking");
                                        }
                                    }
                                }
                            }
                            get_text = get_time[i] = time.getBook();
                            get_time_book[i] = time.getTime();
                            times.add(time);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("gms: ",e.getMessage());
                        }
                    }
                    adapterTime.notifyDataSetChanged();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), "time " + volleyError, Toast.LENGTH_SHORT).show();
                }
            });
            request.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(request);
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
                    bundle.putString("catField", catFieldDetail);
                    bundle.putString("location", location);
                    bundle.putString("dateselected", dateselected);
                    bundle.putString("userid", userID);
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void showPDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Data loading...");
        pDialog.show();
    }

    @Override
    public void itemClicked(View view, int position) {
        for (int j = 0; j < timeBooks.size(); j++) {
            if (get_time[position].equals("Booked")) {
                showDialog();
                break;
            } else {
                Fragment fragment = new FragmentBooking();
                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", get_time_book[position]);
                bundle.putString("date", bookingDay);
                bundle.putString("title", catField);
                bundle.putString("field", field);
                bundle.putString("dayBooking", dayBookingService);
                bundle.putInt("ID", ids);
                bundle.putString("catField", catFieldDetail);
                bundle.putString("location", location);
                bundle.putString("dateselected", dateselected);
                bundle.putString("uid", userID);
                bundle.putString("strdate", strdate);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
            }
        }
    }

    public void showDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("This time booked already")
                .setMessage("Please choose other time!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
