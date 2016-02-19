package com.hammersmith.fustalfootballbookingfield.Container;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hammersmith.fustalfootballbookingfield.Activities.RegisterActivity;
import com.hammersmith.fustalfootballbookingfield.Activities.ViewProfile;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.ContainerFragment;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.hammersmith.fustalfootballbookingfield.widget.CircleTransform;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by USER on 9/25/2015.
 */
public class ContainerApplication extends AppCompatActivity {
    public ContainerFragment containTab;
    public DrawerLayout mDrawer;
    JSONObject response, profile_pic_url, pro_pic_data;
    Fragment contentFragment;
    Context context = ContainerApplication.this;
    NavigationView mNavigationView;
    ImageView profile;
    TextView txtName, txtEmail;
    ActionBarDrawerToggle mDrawerToggle;
    User user;
    private Toolbar toolbar;
    String str = "imperial";

    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    ImageView clearText;
    private GoogleApiClient client;

    public ContainerApplication(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_application_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        editText = (EditText)findViewById(R.id.editText);
        listView = (ListView)findViewById(R.id.listView);
        clearText = (ImageView) findViewById(R.id.search_clear);
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        user = PrefUtils.getCurrentUser(ContainerApplication.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Futsal Booking");

        mDrawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        mNavigationView.addHeaderView(header);

        profile = (ImageView) header.findViewById(R.id.imgPro);
        txtName = (TextView) header.findViewById(R.id.txtPro);
        txtEmail = (TextView) header.findViewById(R.id.txtEmail);
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        Picasso.with(context).load("https://graph.facebook.com/" + user.getFacebookID() + "/picture?type=large").transform(new CircleTransform()).into(profile);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(false);
                mDrawer.closeDrawers();

                if (item.getItemId() == R.id.nav_item_home) {

                }

                if(item.getItemId() == R.id.nav_item_setting){
//                    Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContainerApplication.this, ViewProfile.class);
                    startActivity(intent);
                }

                if (item.getItemId() == R.id.logout) {
                    FacebookSdk.sdkInitialize(getApplicationContext());
                    PrefUtils.clearCurrentUser(ContainerApplication.this);
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(ContainerApplication.this, RegisterActivity.class);
                    startActivity(intent);

                }
                return false;
            }
        });
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.app_name,
                R.string.app_name);
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        if (savedInstanceState == null) {
            initScreen();
        } else {
            containTab = (ContainerFragment) getSupportFragmentManager().getFragments().get(0);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initScreen() {
        containTab = new ContainerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.continer_framelayout, containTab).commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
