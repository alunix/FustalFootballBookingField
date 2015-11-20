package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerHomeAdapter;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 10/29/2015.
 */
public class TabHome extends Fragment implements RecyclerHomeAdapter.ClickListener {
    RecyclerView recyclerView;
    RecyclerHomeAdapter adapter;
    int[] id;
    String[] title;

    List<Field> fields = new ArrayList<Field>();
    Field field;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_home, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerHomeAdapter(getActivity(), fields);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        if (fields.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest fieldReq = new JsonArrayRequest(Constant.URL_LOCATION, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    id = new int[jsonArray.length()];
                    title = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            field = new Field();
                            field.setImage(obj.getString("image_path"));
                            field.setName(obj.getString("name"));
                            field.setLocation(obj.getString("address"));
                            id[i] = obj.getInt("id");
                            title[i] = obj.getString("name");
                            fields.add(field);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), volleyError + "", Toast.LENGTH_SHORT).show();
                }
            });
            AppController.getInstance().addToRequestQueue(fieldReq);

        }

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void itemClicked(View view, int position) {

        //Toast.makeText(getActivity(), "Click Item" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ActivityBooking.class);
        intent.putExtra("title", title[position]);

        intent.putExtra("ID", id[position]);
        startActivity(intent);

    }

}