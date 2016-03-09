package com.hammersmith.fustalfootballbookingfield.Activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerProfile;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewProfile extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton fab;
    public static User user;
    public static UserUpdate userUpdate;
    Context context = ViewProfile.this;
    ImageView imageProGoogle, imageUser;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private ViewPagerProfile adapter;
    ProfilePictureView profilePictureView;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        userUpdate = new UserUpdate();
        user = PrefUtils.getCurrentUser(ViewProfile.this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_view_profile);
        viewPager = (ViewPager) findViewById(R.id.view_pager_view_profile);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_view_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setToolbar();
        adapter = new ViewPagerProfile(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setTitle(user.getName());

        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageProGoogle = (ImageView) findViewById(R.id.userProfileGoogle);
        fab.setOnClickListener(this);

        if (user.getImageProfile() != null){
            profilePictureView.setVisibility(View.GONE);
            Picasso.with(context).load(user.getImageProfile()).into(imageProGoogle);
        }else{
            profilePictureView.setProfileId(user.getFacebookID());
            imageProGoogle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Intent intent = new Intent(ViewProfile.this,EditUserActivity.class);
                intent.putExtra("status","1");
                startActivity(intent);
                finish();
                break;
        }
    }

    public static class MyFragment extends Fragment{
    public static final java.lang.String ARG_PAGE = "arg_page";
        TextView name,email,phone,address,gender,dob;
        public MyFragment(){

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
            View rootView = inflater.inflate(R.layout.fragment_view_profile,container,false);
            name = (TextView) rootView.findViewById(R.id.name);
            email = (TextView) rootView.findViewById(R.id.email);
            phone = (TextView) rootView.findViewById(R.id.phonenumber);
            gender = (TextView) rootView.findViewById(R.id.gender);
            address = (TextView) rootView.findViewById(R.id.address);
            dob = (TextView) rootView.findViewById(R.id.dob);

            JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        userUpdate.setName(jsonObject.getString("username"));
                        userUpdate.setEmail(jsonObject.getString("email"));
                        userUpdate.setGender(jsonObject.getString("gender"));
                        userUpdate.setAddress(jsonObject.getString("address"));
                        userUpdate.setPhone(jsonObject.getString("phone"));
                        userUpdate.setDob(jsonObject.getString("dob"));

                        name.setText(userUpdate.getName());
                        email.setText(userUpdate.getEmail());
                        phone.setText(userUpdate.getPhone());
                        gender.setText(userUpdate.getGender());
                        address.setText(userUpdate.getAddress());
                        dob.setText(userUpdate.getDob());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(),volleyError+"",Toast.LENGTH_SHORT).show();
                }
            });
            AppController.getInstance().addToRequestQueue(objectRequest);

            return rootView;
        }
    }
    public void setToolbar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}


