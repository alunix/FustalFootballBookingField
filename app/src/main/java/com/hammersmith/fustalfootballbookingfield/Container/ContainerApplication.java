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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;
import com.hammersmith.fustalfootballbookingfield.Activities.RegisterActivity;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.ContainerFragment;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabHome;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerHomeAdapter;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.hammersmith.fustalfootballbookingfield.widget.CircleTransform;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
//        initList();
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString().equals("")) {
//                    initList();
//                } else {
//                    searchItem(s.toString());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable ed) {
//
//            }
//        });

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
        txtName.setText(user.name);
        txtEmail.setText(user.email);
        Picasso.with(context).load("https://graph.facebook.com/" + user.facebookID + "/picture?type=large").transform(new CircleTransform()).into(profile);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawer.closeDrawers();

                if (item.getItemId() == R.id.nav_item_home) {

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

    public void testMethod(){
        Log.d("Test", "Searching");
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
    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.toLowerCase().contains(textToSearch.toLowerCase())){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void initList(){
        items = new String[]{"Imperial Stadium","Down Town Sport","CG 7","T-Sport"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.custom_list,R.id.textView,listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),""+ listItems.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContainerApplication.this, ActivityBooking.class);
//                intent.putExtra("key",listItems.get(position));
                startActivity(intent);
            }
        });
    }
}
