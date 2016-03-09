package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Time;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentBooking extends Fragment {
    Button button;
    String dateBooking;
    String date;
    TextView title;
    TextView name, phone, email, gender, address, dob;
    String fields, catField, days, typeFieldDetail;
    public static TextView textDate, textTime, textField;
    User user;
    UserUpdate userUpdate;
    Time time;
    String strtime;
    String bid;
    String location;
    String type;
    String catFieldDetail;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking_field, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        user = PrefUtils.getCurrentUser(getContext());
        name = (TextView) view.findViewById(R.id.name);
        phone = (TextView) view.findViewById(R.id.phone);
        email = (TextView) view.findViewById(R.id.email);
        address = (TextView) view.findViewById(R.id.address);
        gender = (TextView) view.findViewById(R.id.gender);
        dob = (TextView) view.findViewById(R.id.dob);
        textDate = (TextView) view.findViewById(R.id.txtday);
        textField = (TextView) view.findViewById(R.id.txtfield);
        textTime = (TextView) view.findViewById(R.id.txttime);
        title = (TextView) view.findViewById(R.id.title);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    name.setText(jsonObject.getString("username"));
                    email.setText(jsonObject.getString("email"));
                    gender.setText(jsonObject.getString("gender"));
                    address.setText(jsonObject.getString("address"));
                    phone.setText(jsonObject.getString("phone"));
                    dob.setText(jsonObject.getString("dob"));
                    hidePDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
                hidePDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);

        String day = getArguments().getString("dateBooking");
        final String time = getArguments().getString("timeBooking");
        strtime = time;
        textTime.setText(time);
        String date = getArguments().getString("date");
        days = date;
        textDate.setText(date);
        type = getArguments().getString("title");
        catFieldDetail = getArguments().getString("catField");
        textField.setText(type + " (" + catFieldDetail + ")");
        final String field = getArguments().getString("field");
        final String dayBooking = getArguments().getString("dayBooking");
        final int id = getArguments().getInt("ID");
        location = getArguments().getString("location");

        JsonObjectRequest objRequest = new JsonObjectRequest(Constant.URL_CHECKDATE + dayBooking + "/new", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                hidePDialog();
                try {
                    bid = jsonObject.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                hidePDialog();
                Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(objRequest);

        button = (Button) view.findViewById(R.id.btnBooking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Booking successful!", Toast.LENGTH_SHORT).show();
                saveTimeBooking();
                addHistory();
                getActivity().finish();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentTime();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", type);
                    bundle.putString("field", field);
                    bundle.putString("dateBooking", days);
                    bundle.putString("timeBooking", time);
                    bundle.putString("dayBooking", dayBooking);
                    bundle.putString("catField", catFieldDetail);
                    bundle.putInt("ID", id);
                    bundle.putString("location", location);
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    transaction.replace(R.id.layoutBooking, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void saveTimeBooking() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_BOOKING + bid, new Response.Listener<String>() {
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
                params.put("bid", bid);
                params.put("booking_time", strtime);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
    }

    public void addHistory() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_ADDHISTORY + user.getFacebookID(), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                hidePDialog();
//                Toast.makeText(getActivity(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                        hidePDialog();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("location", location);
                params.put("type", type);
                params.put("field", catFieldDetail);
                params.put("date", days);
                params.put("time", strtime);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
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
}
