package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

/**
 * Created by USER on 10/1/2015.
 */
public class TabMap extends RootFragment {
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap mMap;
    SupportMapFragment supportMapFragment;
    MapView mMapView;
    Marker mMarker;
    private static View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_map,container, false);

        mMapView = (MapView) view.findViewById(R.id.maps);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        MapsInitializer.initialize(getActivity());
        mMap = mMapView.getMap();
        mMap.setBuildingsEnabled(true);

        // Latitude Logtitude
        LatLng latLng = new LatLng(11.5565195, 104.9198001);
       // Create Maker
        mMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Brown Coffee"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
                mMarker = mMap.addMarker(new MarkerOptions().position(loc));
                if (mMap != null){
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(15).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }
        };



        return view;
    }
}

