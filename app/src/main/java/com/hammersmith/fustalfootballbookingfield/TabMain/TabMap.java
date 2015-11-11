package com.hammersmith.fustalfootballbookingfield.TabMain;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;

/**
 * Created by USER on 10/1/2015.
 */
public class TabMap extends RootFragment {
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_map,container,false);
            try{
                if(googleMap == null){
                    googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
                }
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                Marker title = googleMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("My Loaction").snippet("Cambodia Kingdom of Wonder"));
            }catch (Exception e){
                e.printStackTrace();
            }
        return view;
    }
}

