package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 10/28/2015.
 */
public class TabLeagueView extends Fragment {
    ProgressDialog mProgress;
    public TabLeagueView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leagueView = inflater.inflate(R.layout.tab_league_view, container, false);
        WebView viewLeague = (WebView) leagueView.findViewById(R.id.webView);
//        viewLeague.getSettings().setJavaScriptEnabled(true);
//        viewLeague.setWebViewClient(new WebViewClient());
        WebSettings settings = viewLeague.getSettings();
        settings.setJavaScriptEnabled(true);
        mProgress = ProgressDialog.show(getActivity(), "Loading",
                "Please wait for a moment...");
        viewLeague.loadUrl("https://www.ligabbva.com");
        viewLeague.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }
            }
        });
        return leagueView;
    }
}
