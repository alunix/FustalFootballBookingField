package com.hammersmith.fustalfootballbookingfield.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerHomeAdapter;

/**
 * Created by USER on 10/29/2015.
 */
public class TabList extends RootFragmgmet implements RecyclerHomeAdapter.ClickListener{
    RecyclerView recyclerView;
    RecyclerHomeAdapter adapter;
    View root;
    TextView bookingField;
    public TabList(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.tab_home, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerHomeAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void itemClicked(View view, int position) {

        Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();

//        Fragment fragment = new TabBooking();
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.layoutHome,fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


    }

}