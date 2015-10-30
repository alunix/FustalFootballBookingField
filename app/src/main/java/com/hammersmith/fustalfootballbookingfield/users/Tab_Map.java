package com.hammersmith.fustalfootballbookingfield.users;

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

/**
 * Created by USER on 10/1/2015.
 */
public class Tab_Map extends RootFragmgmet {
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_map,container, false);

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.Map)).getMap();
//                googleMap = ((MapFragment) getFragmentManager().
//                        findFragmentById(R.id.Map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(TutorialsPoint).title("TutorialsPoint"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
