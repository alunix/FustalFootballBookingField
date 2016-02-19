package com.hammersmith.fustalfootballbookingfield.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerProfile;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

public class ViewProfile extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton fab;
    User user;
    Context context;
    ImageView imageBack, imageEdit, imageUser;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private ViewPagerProfile adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

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

        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageUser = (ImageView) findViewById(R.id.userProfile);
        fab.setOnClickListener(this);
        Picasso.with(context).load("https://graph.facebook.com/" + user.getFacebookID() + "/picture?type=large").into(imageUser);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(this, ActivityUpdate.class));
                break;
        }
    }

    public static class MyFragment extends Fragment{
    public static final java.lang.String ARG_PAGE = "arg_page";
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


