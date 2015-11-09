package com.hammersmith.fustalfootballbookingfield.Test;

import android.widget.ArrayAdapter;

import com.hammersmith.fustalfootballbookingfield.R;

import java.util.ArrayList;

/**
 * Created by USER on 11/7/2015.
 */
public class Home  {
    public String nameField;
    public String LocationField;
    public int photoId;

    public Home(String nameField, String locationField, int photoId) {
        this.nameField = nameField;
        LocationField = locationField;
        this.photoId = photoId;
    }

    public static ArrayList<Home>getHomes(){
        ArrayList<Home> homes = new ArrayList<Home>();
            homes.add(new Home("Down Town Sport","Phnom Penh", R.drawable.imgnaga));
            homes.add(new Home("Imperial Stadium","Kampong Cham", R.drawable.imgemperia));
            homes.add(new Home("Sport Club","Batdom Bong", R.drawable.imgdowntown));
            return homes;
        }

    }
