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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hammersmith.fustalfootballbookingfield.Activities.RegisterActivity;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.ContainerFragment;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.hammersmith.fustalfootballbookingfield.widget.CircleTransform;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by USER on 9/25/2015.
 */
public class ContainerApplication extends AppCompatActivity {

    public ContainerFragment containTab;
    //    RecyclerView mRecyclerView;
//    RecyclerView.Adapter mAdapter;
//    public RecyclerView.LayoutManager mLayoutManager;
    public DrawerLayout mDrawer;
    JSONObject response, profile_pic_url, pro_pic_data;
    Fragment contentFragment;

//    String EMAIL;
//    String NAME;
//    String PROFILE;


//    String TITLES[] ={"Home","Events","Mail","Shop","Travel","Logout"};
//    int ICONS[]={R.drawable.ic_home,R.drawable.ic_event,R.drawable.ic_mail,R.drawable.ic_shop,R.drawable.ic_travel,R.drawable.ic_action_logout};
Context context = ContainerApplication.this;
    NavigationView mNavigationView;
    ImageView profile;
    TextView txtName, txtEmail;
    Context cotext;
    EditText searchView;
    ImageView clearSearch;
    LinearLayout searchContainer;
    ActionBarDrawerToggle mDrawerToggle;
    User user;
    private Toolbar toolbar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


//    SearchView searchView;
//    SearchRecentSuggestions recentSuggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_application_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        searchView = (EditText) findViewById(R.id.search_view);
        clearSearch = (ImageView) findViewById(R.id.search_clear);

        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setText("");
            }
        });


        user= PrefUtils.getCurrentUser(ContainerApplication.this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Futsal Booking");


        mDrawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.header, null);
        mNavigationView.addHeaderView(header);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra(user.name);
//        String image_url = intent.getStringExtra(user.facebookID);
//        String email = intent.getStringExtra(user.email);


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


//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclcerView);
//        mRecyclerView.setHasFixedSize(true);


//        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

        // mAdapter = new MyAdapter(TITLES,ICONS,EMAIL,NAME,PROFILE,this);


//        mRecyclerView.setAdapter(mAdapter);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        // setupDrawerToggle();


//        mDrawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
//        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawer,toolbar,R.string.app_name,R.string.app_name){
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };
//
//        mDrawer.setDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();
//        recentSuggestions = new SearchRecentSuggestions(this, SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) findViewById(R.id.search_view_widget);
//        searchView.setFocusable(false);
//        searchView.setQueryHint("Search");
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        if (savedInstanceState == null) {
            initScreen();
        } else {
            containTab = (ContainerFragment) getSupportFragmentManager().getFragments().get(0);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



//    private void setUserProfile(String jsondata) {
//       try {
//           response = new JSONObject(jsondata);
//           txtName.setText(response.get("name").toString());
//           txtEmail.setText(response.get("email").toString());
//           pro_pic_data = new JSONObject(response.getString("picture").toString());
//           profile_pic_url = new JSONObject(pro_pic_data.getString("data"));
//
//           Picasso.with(this).load(profile_pic_url.getString("url")).transform(new CircleTransform()).into(profile);
//
//       } catch (JSONException e) {
//           e.printStackTrace();
//       }
//    }


    private void initScreen() {
        containTab = new ContainerFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.continer_framelayout, containTab).commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        Bundle extras = intent.getExtras();
//        String userQuery = String.valueOf(extras.get(SearchManager.USER_QUERY));
//        String query = String.valueOf(extras.get(SearchManager.QUERY));
//        recentSuggestions.saveRecentQuery(query, null);
//        Toast.makeText(this, "query: " + query + " user_query: " + userQuery,Toast.LENGTH_SHORT).show();
//        searchView.setQuery(query, false); // leave query text in SearchView
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();

//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "ContainerApplication Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.hammersmith.fustalfootballbookingfield.Container/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "ContainerApplication Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.hammersmith.fustalfootballbookingfield.Container/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
    }
}
