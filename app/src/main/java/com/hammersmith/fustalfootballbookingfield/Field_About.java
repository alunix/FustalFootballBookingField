package com.hammersmith.fustalfootballbookingfield;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by USER on 9/28/2015.
 */
public class Field_About extends RootFragmgmet {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.field_about,container,false);
        return view;
    }
}
