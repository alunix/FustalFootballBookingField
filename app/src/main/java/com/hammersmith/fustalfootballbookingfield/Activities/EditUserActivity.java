package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.DateDialog;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    User user;
    Context context = EditUserActivity.this;
    EditText name, email, phone, address, gender, dob;
    UserUpdate userUpdate;
    String strgender;
    ImageView imageProGoogle;
    ProfilePictureView profilePictureView;
    View view;
    String strstatus;
    String strname, stremail, strgenders, straddress, strdob, strphone;
    private ProgressDialog mProgressDialog;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        userUpdate = new UserUpdate();
        user = PrefUtils.getCurrentUser(EditUserActivity.this);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        showProgressDialog();
        Bundle bundle = getIntent().getExtras();
        final String status = bundle.getString("status");
        strstatus = status;
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        name = (EditText) findViewById(R.id.edname);
        email = (EditText) findViewById(R.id.edemail);
        phone = (EditText) findViewById(R.id.edphonenumber);
        address = (EditText) findViewById(R.id.edaddress);
        gender = (EditText) findViewById(R.id.edgender);
        dob = (EditText) findViewById(R.id.eddob);
        imageProGoogle = (ImageView) findViewById(R.id.imageUserGoogel);

        name.setText(user.getName());
        email.setText(user.getEmail());

        if (user.getImageProfile() != null) {
            profilePictureView.setVisibility(View.GONE);
            Picasso.with(context).load(user.getImageProfile()).into(imageProGoogle);
            if (imageProGoogle.getDrawable() == null) {
                profilePictureView.setVisibility(View.GONE);
                imageProGoogle.setImageResource(R.drawable.default_male_avatar);
            }
        } else {
            imageProGoogle.setVisibility(View.GONE);
            profilePictureView.setProfileId(user.getFacebookID());
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("0")) {
                    showDialog();
                } else if (status.equals("user_not_existed")) {
                    showDialog();
                } else {
                    Intent intent1 = new Intent(EditUserActivity.this, ContainerApplication.class);
                    startActivity(intent1);
                    finish();
                }
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Edit Profile");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

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

                    user.setName(jsonObject.getString("username"));
                    user.setEmail(jsonObject.getString("email"));
                    user.setGender(jsonObject.getString("gender"));
                    user.setAddress(jsonObject.getString("address"));
                    user.setPhone(jsonObject.getString("phone"));
                    user.setDob(jsonObject.getString("dob"));
                    hideProgressDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError + "", Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);
        name.setOnFocusChangeListener(this);
        gender.setOnFocusChangeListener(this);
        phone.setOnFocusChangeListener(this);
        address.setOnFocusChangeListener(this);
        dob.setOnFocusChangeListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                strname = name.getText().toString();
                stremail = email.getText().toString();
                straddress = address.getText().toString();
                strgenders = gender.getText().toString();
                strphone = phone.getText().toString();
                strdob = dob.getText().toString();

                userUpdate.setName(strname);
                userUpdate.setEmail(stremail);
                userUpdate.setAddress(straddress);
                userUpdate.setPhone(strphone);
                userUpdate.setGender(strgender);
                userUpdate.setDob(strdob);

                if (strname.equals(null)) {
                    Toast.makeText(getApplicationContext(), "Please input your name!", Toast.LENGTH_SHORT).show();
                } else if (strgenders.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input your gender!", Toast.LENGTH_SHORT).show();
                } else if (straddress.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input your address!", Toast.LENGTH_SHORT).show();
                } else if (strphone.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input your phone!", Toast.LENGTH_SHORT).show();
                } else if (strdob.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input your date of birth!", Toast.LENGTH_SHORT).show();
                } else {
                    updateUser();
                    Intent intent1 = new Intent(EditUserActivity.this, ContainerApplication.class);
                    startActivity(intent1);
                    finish();
                    break;
                }
        }
    }

    @Override
    public void onBackPressed() {
        if (strstatus.equals("0")) {
            showDialog();
        } else if (strstatus.equals("user_not_existed")) {
            showDialog();
        } else {
            Intent intent1 = new Intent(EditUserActivity.this, ContainerApplication.class);
            startActivity(intent1);
            finish();
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.edgender:
                if (hasFocus) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                                    gender.clearFocus();
                                    phone.setFocusable(true);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    gender.setFocusable(false);
                                    phone.setFocusable(true);
                                }
                            });

                    builder.show();
                }
                break;
            case R.id.eddob:
                if (hasFocus) {
                    DateDialog dateDialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dateDialog.show(ft, "DatePicker");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
                    dob.clearFocus();
                }
                break;
        }
    }

    public void updateUser() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_UPDATE + user.getFacebookID() + "/update", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT).show();
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
                params.put("username", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("phone", phone.getText().toString());
                params.put("gender", gender.getText().toString());
                params.put("address", address.getText().toString());
                params.put("dob", dob.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
    }

    public void showDialog() {
        new AlertDialog.Builder(context)
                .setTitle("Quite App")
                .setMessage("Are you sure want to quite this app?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please wait a moment....");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
    }
}
