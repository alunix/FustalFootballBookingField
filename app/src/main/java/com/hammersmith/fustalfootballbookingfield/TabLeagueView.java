package com.hammersmith.fustalfootballbookingfield;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by USER on 10/28/2015.
 */
public class TabLeagueView extends RootFragmgmet {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leagueView = inflater.inflate(R.layout.tab_league_view,container,false);
        WebView viewLeague = (WebView)leagueView.findViewById(R.id.webView);
        viewLeague.loadUrl("http://m.premierleague.com/en-gb/fixtures.html");
        return leagueView;
    }
}
