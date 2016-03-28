package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;
import com.hammersmith.fustalfootballbookingfield.model.UserHistory;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerHomeAdapter;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 10/29/2015.
 */
public class TabHome extends Fragment implements RecyclerHomeAdapter.ClickListener {
    RecyclerView recyclerView;
    public static RecyclerHomeAdapter adapter;
    private ProgressDialog pDialog;
    int[] id;
    String[] title;
    List<Field> fields = new ArrayList<>();
    Field field;
    String image = "";
    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_home, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recylcerview);
        initList();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (fields.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_LOCATION, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    hidePDialog();
                    id = new int[jsonArray.length()];
                    title = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            field = new Field();
                            field.setImage(Constant.URL_HOME + obj.getString("image_path"));
                            field.setName(obj.getString("name"));
                            field.setLocation(obj.getString("address"));
                            id[i] = obj.getInt("id");
                            title[i] = obj.getString("name");
                            fields.add(field);
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
//                    showDialog();
                    Log.d("error",""+volleyError);
                    hidePDialog();
                }
            });
            AppController.getInstance().addToRequestQueue(fieldReq);
        }
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener(new View.OnKeyListener() {
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

        return root;
    }

    public void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerHomeAdapter(getActivity(), fields);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);
    }

    @Override
    public void itemClicked(View view, int position) {
        image = ((Field)fields.get(position)).getImage();
        Intent intent = new Intent(getActivity(), ActivityBooking.class);
        intent.putExtra("location", title[position]);
        intent.putExtra("field", image);
        intent.putExtra("ID", id[position]);
        startActivity(intent);
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
    public void showDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("No Connection")
                .setMessage("Please retry again!")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment frg = null;
                        frg = getActivity().getSupportFragmentManager().findFragmentById(R.id.layoutHome);
                        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.detach(frg);
                        ft.attach(frg);
                        ft.commit();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}