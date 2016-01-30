package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hammersmith.fustalfootballbookingfield.DateDialog;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.hammersmith.fustalfootballbookingfield.widget.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.hammersmith.fustalfootballbookingfield.R.id.edUsername;
import static com.hammersmith.fustalfootballbookingfield.R.id.tool_bar;

public class ActivitySetting extends AppCompatActivity {
    User user;
    ImageView imageUser;
    EditText userName, email, phone, gender, address, dob;
    Context context = ActivitySetting.this;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_setting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setOnMenuItemClickListener(mMenuItem);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        user = PrefUtils.getCurrentUser(ActivitySetting.this);
        imageUser = (ImageView) findViewById(R.id.imgUser);
        userName = (EditText) findViewById(R.id.edUsername);
        email = (EditText) findViewById(R.id.edEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        gender = (EditText) findViewById(R.id.edgender);
        address = (EditText) findViewById(R.id.edPlace);
        dob = (EditText) findViewById(R.id.eddob);

        Picasso.with(context).load("https://graph.facebook.com/" + user.facebookID + "/picture?type=large").into(imageUser);
        userName.setText(user.name);
        email.setText(user.email);
        phone.setText("096 2008 259");
        gender.setText("Male");
        address.setText("Phnom Pehn");
        dob.setText("2016-01-16");
    }

    @Override
    protected void onStart() {
        super.onStart();
        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dateDialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dateDialog.show(ft, "DatePicker");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
                }
            }
        });
    }

    private Toolbar.OnMenuItemClickListener mMenuItem = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_save:

                    StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_ADDDATA, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            Toast.makeText(getApplicationContext(), "Data uploaded...", Toast.LENGTH_SHORT).show();
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(getApplicationContext(),volleyError.toString(),Toast.LENGTH_SHORT).show();
                                    NetworkResponse networkResponse = volleyError.networkResponse;
                                    if (networkResponse != null) {
                                        Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                                    }
                                    if (volleyError instanceof TimeoutError) {
                                        Log.e("Volley", "TimeoutError");
                                    }else if(volleyError instanceof NoConnectionError){
                                        Log.e("Volley", "NoConnectionError");
                                    } else if (volleyError instanceof AuthFailureError) {
                                        Log.e("Volley", "AuthFailureError");
                                    } else if (volleyError instanceof ServerError) {
                                        Log.e("Volley", "ServerError");
                                    } else if (volleyError instanceof NetworkError) {
                                        Log.e("Volley", "NetworkError");
                                    } else if (volleyError instanceof ParseError) {
                                        Log.e("Volley", "ParseError");
                                    }
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("id", "1");
                            params.put("name", "Thuon");
                            params.put("email", "thuon@gmail.com");
                            params.put("phone", "0962008259");
                            params.put("gender", "Male");
                            params.put("address", "Phnom Penh");
                            params.put("dob", "2016-01-16");

                            return params;
                        }
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();

                            return params;
                        }
                    };
                    AppController.getInstance().addToRequestQueue(userReq);
                    break;

                case R.id.action_cancel:
                    Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }

            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem settingsMenuItem = menu.findItem(R.id.action_save);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle().toString());
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        return super.onPrepareOptionsMenu(menu);
    }
}
