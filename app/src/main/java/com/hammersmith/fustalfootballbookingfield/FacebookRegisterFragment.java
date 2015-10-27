package com.hammersmith.fustalfootballbookingfield;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;

/**
 * Created by HTPP on 08/31/2015.
 */
public class FacebookRegisterFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "FacebookRegisterFragment";
    private Button loginFacebookButton,loginGoogleButton;
    private LoginButton facebookLogin;

    CallbackManager callbackManager;
    public FacebookRegisterFragment (){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        facebookLogin = (LoginButton) view.findViewById(R.id.facebook_login);
        facebookLogin.setFragment(this);
        facebookLogin.setReadPermissions(Arrays.asList("public_profile","user_friends"));
        facebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                Toast.makeText(getContext(), profile.getName().toString(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),CompletedRegisterActivity.class));

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facebook_register_activity,container,false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



        @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
