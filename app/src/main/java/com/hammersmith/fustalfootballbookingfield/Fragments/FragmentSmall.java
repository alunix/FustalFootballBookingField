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

import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerAdapterSmallField;

/**
 * Created by USER on 11/17/2015.
 */
public class FragmentSmall extends Fragment implements RecyclerAdapterSmallField.ClickListener{
    TextView typeField;
    RecyclerView recyclerView;
    RecyclerAdapterSmallField adapter;
    String strTypeField;
    public FragmentSmall(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_small,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapterSmallField(getActivity(),new ContainerApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        typeField = (TextView) view.findViewById(R.id.typeField);
        strTypeField = getArguments().getString("field");
        typeField.setText(strTypeField);

        return view;
    }
    @Override
    public void itemClicked(View view, int position) {
        Fragment fragment = new FragmentCalendarBooking();
        //Toast.makeText(getActivity(),"Item Click "+position,Toast.LENGTH_SHORT ).show();
        Bundle bundle = new Bundle();
        bundle.putString("field",strTypeField);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutSmall,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

