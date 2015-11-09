package com.hammersmith.fustalfootballbookingfield.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.hammersmith.fustalfootballbookingfield.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Arrays;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by minea2015 on 11/2/2015.
 */
public class FacebookRegisterFragment extends Fragment {

   public String id, fullname, email, gender,photo;
    //  private String pro_photo;
    public static final String FULL_NAME = "fullname";
    public static final String EMAIL ="email";
    public static final String GENDER ="gender";
    public static final String PHOTO ="photo";


    LoginButton loginButton;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    ProfileTracker profileTracker;

//    private FacebookCallback<LoginResult>callback = new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//              // if(AccessToken.getCurrentAccessToken()!=null){
//            accessToken = loginResult.getAccessToken();
//                   Profile profile = Profile.getCurrentProfile();
//                   LoginManager.getInstance().logInWithReadPermissions(getActivity(),
//                           Arrays.asList("public_profile", "user_friends", "email", "user_photos"));
//                   profileFb(profile);
//              // }
//
//
//        }
//
//        @Override
//        public void onCancel() {
//
//        }
//
//        @Override
//        public void onError(FacebookException error) {
//
//        }
//    };


    public FacebookRegisterFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        generateHasKey();
        callbackManager = CallbackManager.Factory.create();
        accessToken = AccessToken.getCurrentAccessToken();

//        if (AccessToken.getCurrentAccessToken()!=null){
//           // profileFb();
//        }

//
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            Profile.fetchProfileForCurrentAccessToken();
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
               Profile.setCurrentProfile(currentProfile);
            }

        };

        accessTokenTracker.stopTracking();
        profileTracker.startTracking();
    }

    public void generateHasKey() {
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "hammersmith.fustalfootballbookingfield",
                    PackageManager.GET_SIGNATURES );
        for (android.content.pm.Signature signature : info.signatures){
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            Log.d("KeyHash:", Base64.encodeToString(md.digest(),Base64.DEFAULT));
        }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fbfragment = inflater.inflate(R.layout.facebook_register_activity,container,false);

        loginButton = (LoginButton)fbfragment.findViewById(R.id.login_facebook);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (AccessToken.getCurrentAccessToken()!=null){
                   // Profile profile = Profile.getCurrentProfile();
                    profileFb();
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        return fbfragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loginButton.setReadPermissions(Arrays.asList("public_profile,email"));

        //id=fullname=email=gender=photo="";
        //loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
//        if (AccessToken.getCurrentAccessToken()!=null){
//                profileFb();
//        }
            //  loginButton.registerCallback(callbackManager, callback);

    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
       profileFb();
       // AppEventsLogger.deactivateApp(getActivity());
       // profileFb(Profile.getCurrentProfile());
    }

    @Override
    public void onResume() {
        super.onResume();
     //  Profile profile = Profile.getCurrentProfile();
      // profileFb();
     accessTokenTracker.startTracking();
      profileTracker.startTracking();
     // AppEventsLogger.activateApp(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        accessTokenTracker.stopTracking();
        profileTracker.startTracking();
       profileFb();
      //AppEventsLogger.deactivateApp(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
       // AppEventsLogger.deactivateApp(getActivity());
        profileFb();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void profileFb() {
        Profile profile = Profile.getCurrentProfile();
        if (profile!=null){
            photo = profile.getId().toString();
        }

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        JSONObject json = response.getJSONObject();

                        try {
                            if (json!=null){
                                id = json.getString("id");
                                fullname = json.getString("name");
                                email = json.getString("email");
                               // gender = json.getString("gender");


//                                Toast.makeText(getActivity().getApplicationContext(), "Success !" + fullname, Toast.LENGTH_LONG).show();
//                                Toast.makeText(getActivity().getApplicationContext(), "Success !" + email, Toast.LENGTH_LONG).show();
//                                Toast.makeText(getActivity().getApplicationContext(), "Success !" + gender, Toast.LENGTH_LONG).show();
                            }
                            Intent intent = new Intent(getActivity(),getFacebookProfile.class);
                            intent.putExtra(FULL_NAME,fullname);
                            intent.putExtra(EMAIL, email);
                            //  intent.putExtra(GENDER, gender);
                            intent.putExtra(PHOTO, photo);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
