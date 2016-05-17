package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.hammersmith.fustalfootballbookingfield.Activities.ViewHistory;
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
    FloatingActionButton button;
    String dateBooking;
    String date;
    TextView title;
    TextView name, phone, email, gender, address;
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
    String selecteddate;
    private boolean clicked = false;
    private LinearLayout layout;
    private FrameLayout frameLayout;
    int socketTimeout = 60000;
    String userId;
    int id;
    String strdate;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_booking_field, container, false);
        showpDialog();
        user = PrefUtils.getCurrentUser(getContext());
        name = (TextView) view.findViewById(R.id.name);
        phone = (TextView) view.findViewById(R.id.phone);
        email = (TextView) view.findViewById(R.id.email);
        address = (TextView) view.findViewById(R.id.address);
        gender = (TextView) view.findViewById(R.id.gender);
        textDate = (TextView) view.findViewById(R.id.txtday);
        textField = (TextView) view.findViewById(R.id.txtfield);
        textTime = (TextView) view.findViewById(R.id.txttime);
        title = (TextView) view.findViewById(R.id.title);
        layout = (LinearLayout) view.findViewById(R.id.layoutone);
        frameLayout = (FrameLayout) view.findViewById(R.id.layouttwo);
        String day = getArguments().getString("dateBooking");
        final String time = getArguments().getString("timeBooking");
        strtime = time;
        textTime.setText(time);
        date = getArguments().getString("date");
        days = date;
        textDate.setText(date);
        type = getArguments().getString("title");
        catFieldDetail = getArguments().getString("catField");
        textField.setText(type + " (" + catFieldDetail + ")");
        final String field = getArguments().getString("field");
        final String dayBooking = getArguments().getString("dayBooking");
        id = getArguments().getInt("ID");
        location = getArguments().getString("location");
        selecteddate = getArguments().getString("dateselected");
        bid = getArguments().getString("bid");
        userId = getArguments().getString("uid");
        strdate = getArguments().getString("strdate");
//        JsonObjectRequest objRequest = new JsonObjectRequest(Constant.URL_CHECKDATE + dayBooking, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                try {
//                    bid = jsonObject.getString("id");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(getActivity(), "get bid " + volleyError, Toast.LENGTH_SHORT).show();
//            }
//        });
//        objRequest.setRetryPolicy(policy);
//        AppController.getInstance().addToRequestQueue(objRequest);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                hidePDialog();
                try {
                    name.setText(jsonObject.getString("username"));
                    email.setText(jsonObject.getString("email"));
                    gender.setText(jsonObject.getString("gender"));
                    address.setText(jsonObject.getString("address"));
                    phone.setText(jsonObject.getString("phone"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "booking " + volleyError, Toast.LENGTH_SHORT).show();
            }
        });
        objectRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(objectRequest);
        button = (FloatingActionButton) view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Booking successful!", Toast.LENGTH_SHORT).show();
                if (clicked) {
                    Toast.makeText(getActivity(), "You already booking!", Toast.LENGTH_SHORT).show();
                } else {
                    saveTimeBooking();
                    addHistory();
                    Snackbar snackbar = Snackbar
                            .make(view, "Booking successful!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OPEN", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                Snackbar snackbar1 = Snackbar.make(view, "Message is restored!", Snackbar.LENGTH_SHORT);
//                                snackbar1.show();
                                    Intent intent = new Intent(getActivity(), ViewHistory.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });
                    snackbar.show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    address.setText("");
                    gender.setText("");
                    textDate.setText("");
                    textField.setText("");
                    textTime.setText("");
                    button.setVisibility(View.GONE);
                    layout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                }
                clicked = true;
                view.setFocusableInTouchMode(true);
                view.requestFocus();
                view.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                            getActivity().finish();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    button.setVisibility(View.GONE);
                    Fragment fragment = new FieldTime();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", type);
                    bundle.putString("field", field);
                    bundle.putString("dateBooking", days);
                    bundle.putString("timeBooking", time);
                    bundle.putString("dayBooking", dayBooking);
                    bundle.putString("catField", catFieldDetail);
                    bundle.putInt("ID", id);
                    bundle.putString("location", location);
                    bundle.putString("dateselected", selecteddate);
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
        showpDialog();
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_BOOKING, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                hidePDialog();
//                Toast.makeText(getActivity(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
//                        Toast.makeText(getActivity(), "fragment booking "+volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("booking_time", strtime);
                params.put("booking_date",strdate);
                params.put("uid",userId);
                params.put("fid", String.valueOf(id));
                Log.d("gms ",strtime + "" + strdate +"" + userId +""+id);
                return params;
            }
        };
        userReq.setRetryPolicy(policy);
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
                        Toast.makeText(getActivity(), "fragment booking " + volleyError.toString(), Toast.LENGTH_SHORT).show();
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
        userReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(userReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    public void showpDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    public void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
