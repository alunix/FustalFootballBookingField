package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hammersmith.fustalfootballbookingfield.R.id.edit_query;
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
    User user;

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
                startActivity(new Intent(RegisterActivity.this, GooglePlus.class));
            }
        });
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
                            JSONObject obj = response.getJSONObject();
//                            setProfileToview(obj);
                            try {
                                if (obj != null) {
                                    user = new User();
                                    user.setFacebookID(obj.getString("id"));
                                    user.setName(obj.getString("name"));
                                    user.setEmail(obj.getString("email"));
                                    PrefUtils.setCurrentUser(user, RegisterActivity.this);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Intent main = new Intent(RegisterActivity.this, EditUsers.class);
                            saveUserSocial();
                            startActivity(main);
                            finish();
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

    public void saveUserSocial() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_ADDDATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Toast.makeText(getApplicationContext(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("social_type","1");
                params.put("link", user.getFacebookID());
                params.put("username", user.getName());
                params.put("email", user.getEmail());
                Log.d("facebook id",user.getFacebookID());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    protected void onResume() {
        super.onResume();


    }
}