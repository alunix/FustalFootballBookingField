package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerAdapterTime;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.FieldDetail;
import com.hammersmith.fustalfootballbookingfield.model.Time;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/5/2015.
 */
public class FragmentTimeBooking extends Fragment implements RecyclerAdapterTime.ClickListener {

    RecyclerAdapterTime adapter;
    RecyclerView recyclerView;
    Time time;
    TextView dayBooking;
    String titles[];
    String bookingDay;
    String catField;
    String fieldBooking;
    String dayBookingService;
    int ids;

    public FragmentTimeBooking(){

    }

    List<Time> times = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_booking, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapterTime(getActivity(), times);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        dayBooking = (TextView) view.findViewById(R.id.dayBooking);
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
        Toast.makeText(getActivity(),dayBookToService+"",Toast.LENGTH_SHORT).show();

        if (times.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_BOOK_DETAIL + dayBookToService, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
//                    id = new int[jsonArray.length()];
                    titles = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            time = new Time();
                            time.setTime(obj.getString("time"));
                            titles[i] = obj.getString("time");
                            times.add(time);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
                }
            });
            AppController.getInstance().addToRequestQueue(fieldReq);
        }
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentCustomCalendar();
                    Bundle bundle = new Bundle();
//                    bundle.putString("title", title);
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

        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Fragment fragment = new FragmentBooking();
        Bundle bundle = new Bundle();
        bundle.putString("timeBooking", titles[position]);
        bundle.putString("date", bookingDay);
        bundle.putString("title", catField);
        bundle.putString("field", fieldBooking);
        bundle.putString("dayBooking",dayBookingService);
        bundle.putInt("ID",ids);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();

    }
}
