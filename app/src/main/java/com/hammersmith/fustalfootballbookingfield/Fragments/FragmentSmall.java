package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerAdapterSmallField;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.FieldDetail;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/17/2015.
 */
public class FragmentSmall extends Fragment implements RecyclerAdapterSmallField.ClickListener {
    int socketTimeout = 60000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    TextView typeField;
    RecyclerView recyclerView;
    RecyclerAdapterSmallField adapter;
    String strTypeField;
    String catField;
    int id[];
    String title[];
    FieldDetail fieldDetail;
    String location;
    private ProgressDialog pDialog;
    private String userID;
    User user;

    public FragmentSmall() {

    }

    List<FieldDetail> fieldDetails = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_field_detail, container, false);
        user = PrefUtils.getCurrentUser(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapterSmallField(getActivity(), fieldDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        typeField = (TextView) view.findViewById(R.id.typeField);
        strTypeField = getArguments().getString("field");
        catField = getArguments().getString("title");
        location = getArguments().getString("location");
        typeField.setText(catField);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Constant.URL_GETDATA + user.getFacebookID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    userID = jsonObject.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"get user id "+volleyError, Toast.LENGTH_SHORT).show();
                Log.d("calendar", "" + volleyError);
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);

        if (fieldDetails.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_FIELD_DETAIL + strTypeField, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    id = new int[jsonArray.length()];
                    title = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            fieldDetail = new FieldDetail();
                            fieldDetail.setName(obj.getString("name"));
                            fieldDetail.setWeekMrgPri(obj.getString("weekday_mgr_price"));
                            fieldDetail.setWeekEvePri(obj.getString("weekday_evening_price"));
                            fieldDetail.setWeekendMrgPri(obj.getString("weekend_mgr_price"));
                            fieldDetail.setGetWeekendEvePri(obj.getString("weekend_evening_price"));
                            id[i] = obj.getInt("id");
                            title[i] = obj.getString("name");
                            fieldDetails.add(fieldDetail);
                            hidePDialog();
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
                    hidePDialog();
                }
            });
            fieldReq.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(fieldReq);
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new ActivityBooking.MyFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("location", location);
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    transaction.replace(R.id.layoutSmall, fragment);
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
    public void itemClicked(View view, int position) {
        Fragment fragment = new FragmentCustomCalendar();
        //Toast.makeText(getActivity(),"Item Click "+position,Toast.LENGTH_SHORT ).show();
        Bundle bundle = new Bundle();
        bundle.putString("title", catField);
        bundle.putString("field", strTypeField);
        bundle.putInt("ID", id[position]);
        bundle.putString("catField", title[position]);
        bundle.putString("location", location);
        bundle.putString("userid",userID);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.layoutSmall, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
}
