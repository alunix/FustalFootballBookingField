package com.hammersmith.fustalfootballbookingfield.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hammersmith.fustalfootballbookingfield.R;

//import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by minea2015 on 11/2/2015.
 */
public class Register extends AppCompatActivity {
 private FacebookRegisterFragment facebookRegisterFragment;
 private GoogleRegisterFragment googleRegisterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (savedInstanceState==null){
            facebookRegisterFragment = new FacebookRegisterFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.contentRegister, facebookRegisterFragment).commit();
            googleRegisterFragment = new GoogleRegisterFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.contentRegister,googleRegisterFragment).commit();

        }else {
            facebookRegisterFragment = (FacebookRegisterFragment) getSupportFragmentManager().findFragmentById(R.id.contentRegister);
            googleRegisterFragment = (GoogleRegisterFragment) getSupportFragmentManager().findFragmentById(R.id.contentRegister);

        }
    }
}
