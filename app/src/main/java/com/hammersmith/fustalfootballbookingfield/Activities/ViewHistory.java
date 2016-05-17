package com.hammersmith.fustalfootballbookingfield.Activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterHistory;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerProfile;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.History;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewHistory extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab;
    public static User user;
    public static UserUpdate userUpdate;
    Context context = ViewHistory.this;
    ImageView imageProGoogle, imageUser;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private ViewPagerProfile adapter;
    private AppBarLayout appBarLayout;
    ProfilePictureView profilePictureView;
    String status_history;
    private boolean clicked = false;
    private Activity activity = ViewHistory.this;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        userUpdate = new UserUpdate();
        user = PrefUtils.getCurrentUser(ViewHistory.this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_view_profile);
        viewPager = (ViewPager) findViewById(R.id.view_pager_view_profile);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_view_profile);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setToolbar();
        adapter = new ViewPagerProfile(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        Log.d("user id ",user.getFacebookID());
        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_CHECKUSERLOGIN + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    status_history = jsonObject.getString("status_history");
                    if (status_history.equals("2")) {
                        viewPager.setVisibility(View.VISIBLE);
                        fab.setImageResource(R.drawable.ic_action_action_delete);
                        fab.setTag("delete");
                    } else {
                        viewPager.setVisibility(View.GONE);
                        fab.setImageResource(R.drawable.ic_action_restore_white);
                        fab.setTag("restore");
                    }
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
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        objectRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(objectRequest);

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("User's Histories");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = activity.getWindow();
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(activity.getResources().getColor(R.color.primary_dark));
                    }
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = activity.getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(activity.getResources().getColor(R.color.transparent));
                    }
                    isShow = false;
                }
            }
        });

        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePic);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imageProGoogle = (ImageView) findViewById(R.id.userProfileGoogle);
        fab.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (fab.getTag().equals("delete")) {
                    showClearDialog();
                } else {
                    showDialogRestore();
                }
                break;
        }
    }

    public static class MyFragment extends Fragment implements AdapterHistory.ClickListener {
        public static final java.lang.String ARG_PAGE = "arg_page";
        RecyclerView mRecyclerView;
        LinearLayoutManager mLayoutManager;
        AdapterHistory mAdapter;
        List<History> histories = new ArrayList<>();
        History history;
        ImageView proGoogle;
        ProfilePictureView profilePictureView;
        User user;
        Context context;
        private ProgressDialog pDialog;
        FrameLayout frameLayout;

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
            View rootView = inflater.inflate(R.layout.fragment_view_profile, container, false);
            user = PrefUtils.getCurrentUser(getContext());
            proGoogle = (ImageView) rootView.findViewById(R.id.proGoogle);
            profilePictureView = (ProfilePictureView) rootView.findViewById(R.id.profilePic);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recylcerview);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            frameLayout = (FrameLayout) rootView.findViewById(R.id.layouthistory);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mAdapter = new AdapterHistory(getActivity(), histories);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setHasFixedSize(true);
            mAdapter.setClickListener(this);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Wait a moment...");
            pDialog.show();

            if (histories.size() <= 0) {
                // Creating volley request obj
                JsonArrayRequest leagueReq = new JsonArrayRequest(Constant.URL_HISTORY + user.getFacebookID(), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            int n = i + 1;
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                history = new History();
                                history.setNo(("" + n));
                                history.setUsername(obj.getString("username"));
                                history.setLocation(obj.getString("location"));
                                history.setType(obj.getString("type"));
                                history.setField(obj.getString("field"));
                                history.setDate(obj.getString("date"));
                                history.setTime(obj.getString("time"));
                                histories.add(history);
                                hidePDialog();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
                        hidePDialog();
                    }
                });
                int socketTimeout = 60000;
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                leagueReq.setRetryPolicy(policy);
                AppController.getInstance().addToRequestQueue(leagueReq);
            }

            return rootView;
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

        @Override
        public void itemClicked(View view, int position) {
            Toast.makeText(getContext(), "item " + position, Toast.LENGTH_SHORT).show();
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

    public void showClearDialog() {
        new AlertDialog.Builder(context)
                .setTitle("Clear History")
                .setMessage("Are you sure want to clear all histories?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteHistory();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
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

    public void showDialogRestore() {
        new AlertDialog.Builder(context)
                .setTitle("Restore History")
                .setMessage("Are you sure want to restore your histories?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        restoreHistory();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
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

    public void deleteHistory() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_DELETEHISTORY + user.getFacebookID(), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), "Data deleted", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "history " + volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status_history", "3");
                return params;
            }
        };
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        userReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(userReq);
    }

    public void restoreHistory() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_DELETEHISTORY + user.getFacebookID(), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(getApplicationContext(), "Data restored", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "history " + volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status_history", "2");
                return params;
            }
        };
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        userReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(userReq);
    }
}


