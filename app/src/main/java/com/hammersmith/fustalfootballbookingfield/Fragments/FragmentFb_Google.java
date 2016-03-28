package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hammersmith.fustalfootballbookingfield.Activities.RegisterActivity;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by minea2015 on 12/16/2015.
 */
public class FragmentFb_Google extends Fragment {
    int socketTimeout = 60000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    private CallbackManager callbackManager = null;
    private AccessTokenTracker accessTokenTracker = null;
    private ProfileTracker profileTracker = null;
    private Profile profile;
    LoginButton loginButton;


    public static final String FACEBOOK_ID = "facebookId";
    public static final String FULL_NAME = "fullname";
    public static String IMAGE = "images";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    //  private String FB_AUTHTOKEN;
    public String fbId, fullname, email, gender, photo;

    public FragmentFb_Google() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        getLoginDetails(loginButton);
    }

    private void getLoginDetails(LoginButton loginButton) {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                RequestData(loginResult);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fbFragment = inflater.inflate(R.layout.google_facebook_layout, container, false);

        return fbFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (LoginButton) getActivity().findViewById(R.id.login_button);
        loginButton.setFragment(this);
        // loginButton.registerCallback(callbackManager,mCallback);
    }

    private void RequestData(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), ContainerApplication.class);
                        intent.putExtra("jsondata", object.toString());
                        intent.putExtra("image", object.toString());
                        startActivity(intent);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,gender,picture.width(120).height(120)");
        request.setParameters(parameters);
        request.executeAsync();

    }


    @Override
    public void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(getActivity());

    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(getActivity());

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

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
