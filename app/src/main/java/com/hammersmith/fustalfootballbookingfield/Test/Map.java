package com.hammersmith.fustalfootballbookingfield.Test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 11/11/2015.
 */
public class Map extends Fragment{
    GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_map,container,false);

        try{
            initizeMap();
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    private void initizeMap() {
        if(googleMap == null){
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            if(googleMap == null){
                Toast.makeText(getActivity(),"Sorry unable to open map",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initizeMap();
    }
}
