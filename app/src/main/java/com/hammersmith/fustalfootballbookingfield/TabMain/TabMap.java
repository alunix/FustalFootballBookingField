package com.hammersmith.fustalfootballbookingfield.TabMain;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by USER on 10/1/2015.
 */
public class TabMap extends RootFragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;
    private static View view;
    MapView mMapView;
    Marker mMarker;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view != null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try{
            view = inflater.inflate(R.layout.tab_map,container,false);
        }catch (InflateException e){

        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if(supportMapFragment == null){
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map,supportMapFragment).commit();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        LatLng loc = new LatLng(0,0);
        mMap.addMarker(new MarkerOptions().position(loc).title("My Location"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

