package com.hammersmith.fustalfootballbookingfield.Activities;

import android.graphics.Typeface;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.hammersmith.fustalfootballbookingfield.Fragments.FragmentSmall;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.BookingViewPager;
import com.hammersmith.fustalfootballbookingfield.adapter.RecylerCateFieldAdapter;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.CategoryField;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityBooking extends AppCompatActivity {
    FragmentManager fragmentManager;
    private Fragment contentFragment;
    private CoordinatorLayout mCoordinator;
    private CollapsingToolbarLayout mCollapsingToolBarLayout;
    private Toolbar mToolbar;
    public static ViewPager mPager;
    private DrawerLayout mDrawerLayout;
    public static BookingViewPager mAdapter;
    private TabLayout mTabLayout;
    Typeface typeface;

    NetworkImageView cover;
    String title;

    public static int field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        cover = (NetworkImageView) findViewById(R.id.image_field);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.root_coordinator);
        mCollapsingToolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(mToolbar);
        mCollapsingToolBarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        mAdapter = new BookingViewPager(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("field");
        Bundle bundle = this.getIntent().getExtras();
        field = bundle.getInt("ID");

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Armegoe.ttf");

        mCollapsingToolBarLayout.setExpandedTitleTypeface(typeface);
        mCollapsingToolBarLayout.setTitle(title);
        cover.setImageUrl(image, imageLoader);
    }

    public static class MyFragment extends Fragment implements RecylerCateFieldAdapter.ClickListener {
        RecyclerView recyclerView;
        RecylerCateFieldAdapter adapter;
        int[] id;
        String[] title;

        List<CategoryField> categoryFields = new ArrayList<>();
        CategoryField categoryField;
        private static final java.lang.String ARG_PAGE = "arg_page";

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
            View v = inflater.inflate(R.layout.fragment_chosing_field, container, false);
            categoryField = new CategoryField();

            recyclerView = (RecyclerView) v.findViewById(R.id.recylcerview);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
//            ------This one create for JsonArray-----/
            adapter = new RecylerCateFieldAdapter(getActivity(), categoryFields);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            adapter.setClickListener(this);

            if (categoryFields.size() <= 0) {
                // Creating volley request obj
                JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_CATEGORY + field, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        id = new int[jsonArray.length()];
                        title = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                categoryField = new CategoryField();
                                categoryField.setName(obj.getString("name"));
                                categoryField.setImage(Constant.URL_HOME + obj.getString("path"));
                                id[i] = obj.getInt("id");
                                title[i] = obj.getString("name");
                                categoryFields.add(categoryField);
//                                Toast.makeText(getContext(),Constant.URL_HOME + obj.getString("path"),Toast.LENGTH_SHORT).show();
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

            v.setFocusableInTouchMode(true);
            v.requestFocus();
            v.setOnKeyListener(new View.OnKeyListener() {
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
            return v;
        }

        @Override
        public void itemClicked(View view, int position) {

            Fragment fragment = new FragmentSmall();
            Bundle bundle = new Bundle();
            bundle.putString("field", field + "/" + id[position]);
            bundle.putString("title", title[position]);
            fragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.layoutTypeField, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}