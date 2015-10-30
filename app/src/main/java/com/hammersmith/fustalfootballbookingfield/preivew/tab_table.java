package com.hammersmith.fustalfootballbookingfield.preivew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragmgmet;

/**
 * Created by USER on 9/18/2015.
 */
public class tab_table extends RootFragmgmet {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View table = inflater.inflate(R.layout.tab_table,container,false);
        return table;
    }
}
