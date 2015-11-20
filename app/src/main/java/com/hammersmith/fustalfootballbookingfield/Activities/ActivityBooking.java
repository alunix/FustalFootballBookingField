package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;

import com.hammersmith.fustalfootballbookingfield.Fragments.FragmentSmall;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.BookingViewPager;
import com.hammersmith.fustalfootballbookingfield.Fragments.FragmentCalendarBooking;

public class ActivityBooking extends AppCompatActivity {

    private CoordinatorLayout mCoordinator;
    private CollapsingToolbarLayout mCollapsingToolBarLayout;
    private Toolbar mToolbar;
    private ViewPager mPager;
    private DrawerLayout mDrawerLayout;
    private BookingViewPager mAdapter;
    private TabLayout mTabLayout;

    ImageView cover;
    String title;
    int field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        cover = (ImageView) findViewById(R.id.image_field);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.root_coordinator);
        mCollapsingToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(mToolbar);

        mAdapter = new BookingViewPager(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        title = getIntent().getStringExtra("title");

        Bundle bundle = this.getIntent().getExtras();
        field = bundle.getInt("field");

        mCollapsingToolBarLayout.setTitle(title);
        cover.setImageResource(field);
    }

    public static class MyFragment extends Fragment{
        String small = "Small Field";
        String medium = "Medium Field";
        String large = "Large Field";
        private static final java.lang.String ARG_PAGE = "arg_page";
        public MyFragment(){

        }
        public static MyFragment newInstance(int pageNumber){
            MyFragment myFragment = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE, pageNumber + 1);
            myFragment.setArguments(arguments);
            return myFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_chosing_field,container,false);
            Button buttonSmall = (Button)v.findViewById(R.id.btnSmall);
            buttonSmall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FragmentSmall();

                    Bundle argument = new Bundle();
                    argument.putString("field",small);
                    fragment.setArguments(argument);

                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layoutAllField, fragment);
//                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            Button buttonMedium = (Button)v.findViewById(R.id.btnMedium);
            buttonMedium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FragmentSmall();

                    Bundle argument = new Bundle();
                    argument.putString("field",medium);
                    fragment.setArguments(argument);

                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layoutAllField,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });

            Button buttonLarge = (Button)v.findViewById(R.id.btnLarge);
            buttonLarge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FragmentSmall();

                    Bundle argument = new Bundle();
                    argument.putString("field",large);
                    fragment.setArguments(argument);

                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.layoutAllField,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            return v;
        }
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if(count == 0){
            super.onBackPressed();
        }
        else{
            getFragmentManager().popBackStack();
        }
    }
}
