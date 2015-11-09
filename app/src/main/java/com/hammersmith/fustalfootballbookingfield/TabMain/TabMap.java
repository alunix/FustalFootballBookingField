package com.hammersmith.fustalfootballbookingfield.TabMain;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

/**
 * Created by USER on 10/1/2015.
 */
public class TabMap extends RootFragment {
    private GoogleMap googleMap;
    private MapView mapView;
    private boolean mapsSupport = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try{
//            MapsInitializer.initialize(getActivity());
//        }catch (GooglePlayServicesNotAvailableException e){
//            mapsSupport = false;
//        }
        initializeMap();
    }

    private void initializeMap() {
        if(googleMap == null && mapsSupport){
            mapView = (MapView)getActivity().findViewById(R.id.map);
            googleMap = mapView.getMap();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final LinearLayout parent = (LinearLayout)inflater.inflate(R.layout.tab_map,container,false);
        mapView = (MapView)parent.findViewById(R.id.map);
        return parent;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        initializeMap();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}

