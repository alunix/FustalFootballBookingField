package com.hammersmith.fustalfootballbookingfield.TabBooking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragmgmet;

/**
 * Created by USER on 11/4/2015.
 */
public class TabSetting extends RootFragmgmet {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_setting,container,false);
        return view;
    }
}
