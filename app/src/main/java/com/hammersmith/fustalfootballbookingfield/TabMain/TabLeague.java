package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterLeague;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.model.League;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2015.
 */
public class TabLeague extends Fragment implements AdapterLeague.ClickListener {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    AdapterLeague mAdapter;
    int[] id;
    String[] url;
    List<League> leagues = new ArrayList<>();
    League league;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_league_list,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new AdapterLeague(getActivity(),leagues);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mAdapter.setClickListener(this);

        if (leagues.size() <= 0) {
            // Creating volley request obj
            JsonArrayRequest leagueReq = new JsonArrayRequest(Constant.URL_LEAGUE, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    id = new int[jsonArray.length()];
                    url = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            league = new League();
                            league.setImage(Constant.URL_HOME + obj.getString("image_path"));
                            id[i] = obj.getInt("id");
                            url[i] = obj.getString("url");
                            leagues.add(league);

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
                }
            });
            AppController.getInstance().addToRequestQueue(leagueReq);

        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
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
        return view;
    }


    @Override
    public void itemClicked(View view, int position) {
        Fragment fragment = new TabLeagueView();
        Bundle bundle = new Bundle();
        bundle.putString("url",url[position]);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.layoutLeague, fragment).commit();

    }

}
