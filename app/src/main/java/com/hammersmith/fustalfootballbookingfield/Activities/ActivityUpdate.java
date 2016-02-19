package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hammersmith.fustalfootballbookingfield.DateDialog;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerProfile;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerUpdate;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ActivityUpdate extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab;
    User user;
    public static UserUpdate userUpdate;
    Context context;
    ImageView imageBack, imageEdit, imageUser;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private ViewPagerUpdate adapter;
    public static String strgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_update);

        userUpdate = new UserUpdate();
        user = PrefUtils.getCurrentUser(ActivityUpdate.this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_view_profile);
        viewPager = (ViewPager) findViewById(R.id.view_pager_view_profile);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_view_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setToolbar();
        adapter = new ViewPagerUpdate(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageUser = (ImageView) findViewById(R.id.userProfile);
        fab.setOnClickListener(this);
        Picasso.with(context).load("https://graph.facebook.com/" + user.getFacebookID() + "/picture?type=large").into(imageUser);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                updateUser();
                Toast.makeText(getApplicationContext(), "name "+ userUpdate.getName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "email "+ userUpdate.getEmail(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "phone "+ userUpdate.getPhone(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "gender "+ userUpdate.getGender(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "address "+ userUpdate.getAddress(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "dob "+ userUpdate.getDob(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static class MyFragment extends Fragment implements View.OnFocusChangeListener{
        public static final java.lang.String ARG_PAGE = "arg_page";
        EditText userName, email, phone, gender, address, dob;
        User user;
        public String userID;

        public MyFragment() {

        }

        public static MyFragment newInstance(int pageNumber) {
            MyFragment myFragment = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE, pageNumber + 1);
            myFragment.setArguments(arguments);
            return myFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_update, container, false);
            user = PrefUtils.getCurrentUser(getContext());
            userName = (EditText) rootView.findViewById(R.id.edname);
            email = (EditText) rootView.findViewById(R.id.edemail);
            phone = (EditText) rootView.findViewById(R.id.edphonenumber);
            gender = (EditText) rootView.findViewById(R.id.edgender);
            address = (EditText) rootView.findViewById(R.id.edaddress);
            dob = (EditText) rootView.findViewById(R.id.eddob);

            userName.setText(user.getName());
            email.setText(user.getEmail());
            userID = user.getFacebookID();

            userName.setOnFocusChangeListener(this);
            email.setOnFocusChangeListener(this);
            phone.setOnFocusChangeListener(this);
            gender.setOnFocusChangeListener(this);
            address.setOnFocusChangeListener(this);
            dob.setOnFocusChangeListener(this);

            return rootView;
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            switch (view.getId()) {
                case R.id.edname:
                    if (hasFocus) {
                        String strname = userName.getText().toString();
                        userUpdate.setName(strname);
                    }
                    break;
                case R.id.edphonenumber:
                    if (hasFocus) {
                        String strphone = phone.getText().toString();
                        userUpdate.setPhone(strphone);
                    }
                    break;
                case R.id.edaddress:
                    if (hasFocus) {
                        String straddress = address.getText().toString();
                        userUpdate.setAddress(straddress);
                    }
                    break;
                case R.id.edgender:
                    if (hasFocus) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        final CharSequence[] array = {"Male", "Female"};
                        builder.setTitle("Select Gender")
                                .setSingleChoiceItems(array, 2, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        strgender = (String) array[which];
                                    }
                                })

                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        gender.setText(strgender);
                                        userUpdate.setGender(strgender);
                                        gender.clearFocus();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        builder.show();
                    }
                    break;
                case R.id.eddob:
                    if (hasFocus) {
                        DateDialog dateDialog = new DateDialog(view);
                        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                        dateDialog.show(ft, "DatePicker");
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
                        String strdob = dob.getText().toString();
                        userUpdate.setDob(strdob);
                        dob.clearFocus();
                    }
                    break;
            }
        }
    }

    public void setToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void updateUser() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_UPDATE + user.getFacebookID() + "/update", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Toast.makeText(getApplicationContext(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", userUpdate.getName());
                params.put("email", userUpdate.getEmail());
                params.put("phone", userUpdate.getPhone());
                params.put("gender", userUpdate.getGender());
                params.put("address", userUpdate.getAddress());
                params.put("dob", userUpdate.getDob());

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
    }
}