package com.hammersmith.fustalfootballbookingfield;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by USER on 9/15/2015.
 */
public class MainTeam extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.all_team);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment fragment = null;
                        if(v == findViewById(R.id.imageShowTeam)){
                            fragment = new android.app.Fragment();
                        }
                if (v == findViewById(R.id.btnJoinMember)){
                    fragment = new android.app.Fragment();
                }
                if(v == findViewById(R.id.btnPlayWith)){
                    fragment = new android.app.Fragment();
                }
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
               // android.support.v4.app.FragmentTransaction replace = transaction.replace(R.id.output, fragment);
                transaction.commit();
            }
        };
        ImageView teamgroup = (ImageView)findViewById(R.id.imageShowTeam);
        teamgroup.setOnClickListener(listener);
        ImageView teampost = (ImageView)findViewById(R.id.imageShowTeam);
        teampost.setOnClickListener(listener);
        //ImageView teamabout = (ImageView)findViewById(R.id.btnPlayWith);
        //teamabout.setOnClickListener(listener);
    }
}
