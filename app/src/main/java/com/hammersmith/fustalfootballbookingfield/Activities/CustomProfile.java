package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Thuon on 2/26/2016.
 */
public class CustomProfile extends AppCompatActivity {
    public static User user;
    UserUpdate userUpdate;
    Context context = CustomProfile.this;
    ProfilePictureView profilePictureView;
    ImageView proGoogle;
    TextView username, useremail, name, email, phone, address, dob, gender;
    FloatingActionButton fab;
    private ProgressDialog mProgressDialog;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_frofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        showProgressDialog();
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        proGoogle = (ImageView) findViewById(R.id.proGoogle);
        username = (TextView) findViewById(R.id.username);
        useremail = (TextView) findViewById(R.id.useremail);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        gender = (TextView) findViewById(R.id.gender);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        dob = (TextView) findViewById(R.id.dob);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        user = PrefUtils.getCurrentUser(context);
        userUpdate = new UserUpdate();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("User's Information");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (user.getImageProfile() != null) {
            profilePictureView.setVisibility(View.GONE);
            Picasso.with(context).load(user.getImageProfile()).into(proGoogle);
            if (proGoogle.getDrawable() == null) {
                profilePictureView.setVisibility(View.GONE);
                proGoogle.setImageResource(R.drawable.default_male_avatar);
            }
        } else {
            proGoogle.setVisibility(View.GONE);
            profilePictureView.setProfileId(user.getFacebookID());
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    username.setText(jsonObject.getString("username"));
                    useremail.setText(jsonObject.getString("email"));
                    name.setText(jsonObject.getString("username"));
                    email.setText(jsonObject.getString("email"));
                    gender.setText(jsonObject.getString("gender"));
                    address.setText(jsonObject.getString("address"));
                    phone.setText(jsonObject.getString("phone"));
                    dob.setText(jsonObject.getString("dob"));

                    userUpdate.setName(jsonObject.getString("username"));
                    userUpdate.setEmail(jsonObject.getString("email"));
                    userUpdate.setGender(jsonObject.getString("gender"));
                    userUpdate.setAddress(jsonObject.getString("address"));
                    userUpdate.setPhone(jsonObject.getString("phone"));
                    userUpdate.setDob(jsonObject.getString("dob"));
                    hideProgressDialog();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "custom profile "+volleyError, Toast.LENGTH_SHORT).show();
                Log.d("error",""+volleyError);
                hideProgressDialog();
            }
        });

        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        objectRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(objectRequest);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomProfile.this,EditUserActivity.class);
                intent.putExtra("status", "1");
                startActivity(intent);
                finish();
            }
        });
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Data loading....");
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
