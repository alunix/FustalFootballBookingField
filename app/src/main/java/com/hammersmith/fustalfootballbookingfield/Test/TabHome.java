package com.hammersmith.fustalfootballbookingfield.Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.preivew.Booking;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

import java.util.ArrayList;

/**
 * Created by USER on 11/4/2015.
 */
public class TabHome extends RootFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home,container,false);
        ArrayList<Home>arrayList = Home.getHomes();
        AdapterListView adapter = new AdapterListView(getActivity(),arrayList);
        ListView listView = (ListView)view.findViewById(R.id.recylcerview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Clicked item "+position,Toast.LENGTH_SHORT).show();

//                Fragment fragment = new Booking();
//                FragmentManager fragmentManager = getChildFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.layoutBooking,fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
