package com.hammersmith.fustalfootballbookingfield.Container;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hammersmith.fustalfootballbookingfield.users.MainActivity;
import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 9/25/2015.
 */
public class ContainerApplication extends AppCompatActivity {
    private MainActivity containTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_framelayout);
        if(savedInstanceState == null){
            initScreen();
        }else {
            containTab = (MainActivity)getSupportFragmentManager().getFragments().get(0);
        }
    }
    private void initScreen() {
        containTab = new MainActivity();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.continer_framelayout,containTab).commit();
    }
    @Override
    public void onBackPressed() {
        if(!containTab.onBackPress()){
            finish();
        }
        else {

        }
    }
    public void switchContent(int id, Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id,fragment,null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
