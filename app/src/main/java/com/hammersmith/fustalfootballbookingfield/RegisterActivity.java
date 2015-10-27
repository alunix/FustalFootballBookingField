package com.hammersmith.fustalfootballbookingfield;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;


public class RegisterActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    private SignInButton signInButtonGoogle;
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;
    private FacebookRegisterFragment facebookRegisterFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signInButtonGoogle = (SignInButton) findViewById(R.id.google_login);
        signInButtonGoogle.setOnClickListener(this);
        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        if(savedInstanceState == null){
            facebookRegisterFragment = new FacebookRegisterFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.Container_api_login,facebookRegisterFragment).commit();
        }
        else {

        }
    }
    private void addFragmentInContainer(int idContainer,Fragment fragment){

    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.google_login){
            Intent activityConfirm = new Intent(RegisterActivity.this,CompletedRegisterActivity.class);
            startActivity(activityConfirm);
            overridePendingTransition(R.animator.slide_in_left,R.animator.slide_out_right);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    public void showErrorDialog(ConnectionResult connectionResult){
        AlertDialog.Builder builder = new  AlertDialog.Builder(getApplicationContext(),R.style.MyAlertDialog);
        builder.setTitle("Error Sign In");
        builder.setMessage(connectionResult.toString());
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();
            }
        });
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();

            }
        });
    }
}
