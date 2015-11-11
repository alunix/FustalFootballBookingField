package com.hammersmith.fustalfootballbookingfield.Container;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.app.SuggestionProvider;
import com.hammersmith.fustalfootballbookingfield.TabMain.MainActivity;
import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 9/25/2015.
 */
public class ContainerApplication extends AppCompatActivity {
    private MainActivity containTab;
    SearchView searchView;
    SearchRecentSuggestions recentSuggestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_application_layout);

        recentSuggestions = new SearchRecentSuggestions(this, SuggestionProvider.AUTHORITY,SuggestionProvider.MODE);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) findViewById(R.id.search_view_widget);
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        if(savedInstanceState == null){
            initScreen();
        }else {
            containTab = (MainActivity)getSupportFragmentManager().getFragments().get(0);
        }
    }
    private void initScreen() {
        containTab = new MainActivity();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.continer_framelayout,containTab).commit();
    }
    @Override
    public void onBackPressed() {
        if(!containTab.onBackPress()){
            finish();
        }
        else {

        }
    }
    public void switchContent(int id, Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id,fragment,null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        String userQuery = String.valueOf(extras.get(SearchManager.USER_QUERY));
        String query = String.valueOf(extras.get(SearchManager.QUERY));
        recentSuggestions.saveRecentQuery(query, null);
        Toast.makeText(this, "query: " + query + " user_query: " + userQuery,
                Toast.LENGTH_SHORT).show();
        searchView.setQuery(query, false); // leave query text in SearchView
    }
}
