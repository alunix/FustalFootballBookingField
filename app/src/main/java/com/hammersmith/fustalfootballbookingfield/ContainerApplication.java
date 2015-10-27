package com.hammersmith.fustalfootballbookingfield;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by USER on 9/25/2015.
 */
public class ContainerApplication extends AppCompatActivity {
    private MainActivity conntainTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_framelayout);
        if(savedInstanceState == null){
            initScreen();
        }else {
            conntainTab = (MainActivity)getSupportFragmentManager().getFragments().get(0);
        }
    }

    private void initScreen() {
        conntainTab = new MainActivity();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.continer_framelayout,conntainTab).commit();

    }

    @Override
    public void onBackPressed() {
        if(!conntainTab.onBackPress()){

        }
        else {

        }
    }
}
