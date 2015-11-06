package com.hammersmith.fustalfootballbookingfield.preivew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

/**
 * Created by USER on 9/15/2015.
 */
public class team_about extends RootFragment {
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_about, container, false);

        return view;
    }
}
