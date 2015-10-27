package com.hammersmith.fustalfootballbookingfield;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by USER on 9/22/2015.
 */
public class team_member extends RootFragmgmet {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_member,container,false);
        return view;
    }
}
