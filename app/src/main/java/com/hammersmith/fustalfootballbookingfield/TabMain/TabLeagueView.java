package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 10/28/2015.
 */
public class TabLeagueView extends Fragment {

    public TabLeagueView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leagueView = inflater.inflate(R.layout.tab_league_view, container, false);
        WebView viewLeague = (WebView) leagueView.findViewById(R.id.webView);
//        viewLeague.getSettings().setJavaScriptEnabled(true);
//        viewLeague.setWebViewClient(new WebViewClient());
        viewLeague.loadUrl("http://m.premierleague.com/en-gb/fixtures.html");
        return leagueView;
    }
}
