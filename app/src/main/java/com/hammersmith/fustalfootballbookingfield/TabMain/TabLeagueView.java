package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

/**
 * Created by USER on 10/28/2015.
 */
public class TabLeagueView extends RootFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leagueView = inflater.inflate(R.layout.tab_league_view,container,false);
        WebView viewLeague = (WebView)leagueView.findViewById(R.id.webView);
        viewLeague.loadUrl("http://m.premierleague.com/en-gb/fixtures.html");
        return leagueView;
    }
}
