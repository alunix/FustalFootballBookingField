package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabBooking.TabSetting;
import com.hammersmith.fustalfootballbookingfield.login.FacebookRegisterFragment;
import com.hammersmith.fustalfootballbookingfield.login.GoogleRegisterFragment;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

/**
 * Created by USER on 11/9/2015.
 */
public class TabSettingLogin extends RootFragment {

    private FacebookRegisterFragment facebookRegisterFragment;
    private GoogleRegisterFragment googleRegisterFragment;
public TabSettingLogin(){

}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewSetting = inflater.inflate(R.layout.tab_setting_login,container,false);

        if (savedInstanceState==null){
            facebookRegisterFragment = new FacebookRegisterFragment();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.contentRegister, facebookRegisterFragment).commit();
//            googleRegisterFragment = new GoogleRegisterFragment();
//            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentRegister, googleRegisterFragment).commit();

        }else {
            facebookRegisterFragment = (FacebookRegisterFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.contentRegister);
//            googleRegisterFragment = (GoogleRegisterFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.contentRegister);
//
        }
        return viewSetting;
    }


}
