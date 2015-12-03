package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerAdapterSmallField;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.CategoryField;
import com.hammersmith.fustalfootballbookingfield.model.FieldDetail;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/17/2015.
 */
public class FragmentSmall extends Fragment implements RecyclerAdapterSmallField.ClickListener {
    TextView typeField;
    RecyclerView recyclerView;
    RecyclerAdapterSmallField adapter;
    String strTypeField;
    String catField;
    int id[];
    String title[];
    FieldDetail fieldDetail;
    public FragmentSmall() {

    }
    List<FieldDetail> fieldDetails = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_small, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapterSmallField(getActivity(),fieldDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        typeField = (TextView) view.findViewById(R.id.typeField);
        strTypeField = getArguments().getString("field");
        catField = getArguments().getString("title");
        typeField.setText(catField);

        if (fieldDetails.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_FIELD_DETAIL+strTypeField, new Response.Listener<JSONArray>() {
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

//                            Toast.makeText(getActivity(), obj.getString("name"), Toast.LENGTH_SHORT).show();
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
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {
        Fragment fragment = new FragmentCalendarBooking();
        //Toast.makeText(getActivity(),"Item Click "+position,Toast.LENGTH_SHORT ).show();
        Bundle bundle = new Bundle();
        bundle.putString("field", catField);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutSmall, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

