package com.hammersmith.fustalfootballbookingfield;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by USER on 9/15/2015.
 */
public class team_all extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_team, viewGroup, false);
        return view;
    }
}
