package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hammersmith.fustalfootballbookingfield.BuildConfig;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.hammersmith.fustalfootballbookingfield.R.id.login_button;
import static com.hammersmith.fustalfootballbookingfield.R.id.logout;
import static com.hammersmith.fustalfootballbookingfield.R.id.useLogo;

/**
 * Created by minea2015 on 11/18/2015.
 */
public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {


    private static final String TAG = "RegisterActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;


    LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    ProfileTracker profileTracker;
    private GraphResponse response;
    User user;


    public static final String FACEBOOK_ID = "facebookId";
    public static final String FULL_NAME = "fullname";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public String fbId, fullname, email, gender, photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_register);
        callbackManager = CallbackManager.Factory.create();


        if (PrefUtils.getCurrentUser(RegisterActivity.this) != null) {
            Intent home = new Intent(RegisterActivity.this, ContainerApplication.class);
            startActivity(home);
            finish();
        }
        signInButton = (SignInButton) findViewById(R.id.google);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile,email,user_birthday"));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, mCallback);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,GooglePlus.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void setProfileToview(JSONObject object) {
        try {

            if (object != null) {
                user = new User();
                user.facebookID = object.getString("id");
                user.name = object.getString("name");
                user.email = object.getString("email");
                user.gender = object.getString("gender");
                PrefUtils.setCurrentUser(user, RegisterActivity.this);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent main = new Intent(RegisterActivity.this, ContainerApplication.class);
        startActivity(main);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            // Profile profile = Profile.getCurrentProfile();
                            setProfileToview(object);
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };


    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}