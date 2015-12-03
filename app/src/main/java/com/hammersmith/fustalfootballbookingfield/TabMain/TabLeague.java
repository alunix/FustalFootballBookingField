package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterLeague;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerAdapterSmallField;

/**
 * Created by USER on 12/3/2015.
 */
public class TabLeague extends Fragment implements AdapterLeague.ClickListener {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_league_list,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recylcerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterLeague();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void itemClicked(View view, int position) {

    }
}
