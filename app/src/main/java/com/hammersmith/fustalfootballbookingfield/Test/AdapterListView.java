package com.hammersmith.fustalfootballbookingfield.Test;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.Test.Home;

import java.util.ArrayList;

/**
 * Created by USER on 11/6/2015.
 */
public class AdapterListView extends ArrayAdapter<Home> {
    ImageView cover;
    TextView nameField;
    TextView locationField;

    public AdapterListView(Context context, ArrayList<Home> homes) {
        super(context,0, homes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Home homes = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_cardview_list,parent,false);
        }
        cover = (ImageView) convertView.findViewById(R.id.cover);
        nameField = (TextView) convertView.findViewById(R.id.nameField);
        locationField = (TextView) convertView.findViewById(R.id.locationField);

        cover.setImageResource(homes.photoId);
        nameField.setText(homes.nameField);
        locationField.setText(homes.LocationField);

        return convertView;
    }
}
