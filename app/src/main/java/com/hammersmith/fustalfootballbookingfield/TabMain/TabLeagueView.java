package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 10/28/2015.
 */
public class TabLeagueView extends Fragment {
    String url = "";
    ProgressDialog mProgress;
    public TabLeagueView() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View leagueView = inflater.inflate(R.layout.custom_league_view, container, false);
        final WebView viewLeague = (WebView) leagueView.findViewById(R.id.webView);

        url = getArguments().getString("url");
        WebSettings settings = viewLeague.getSettings();
        settings.setJavaScriptEnabled(true);
        mProgress = ProgressDialog.show(getActivity(), "Loading",
                "Please wait for a moment...");
        viewLeague.loadUrl(url);
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
        viewLeague.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && viewLeague.canGoBack()) {
                    viewLeague.goBack();
                    return true;
                }else{
                    Fragment fragment = new TabLeague();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.layoutLeagueView,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                return false;
            }
        });

        leagueView.setFocusableInTouchMode(true);
        leagueView.requestFocus();
        leagueView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new TabLeague();
                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.layoutLeagueView,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return leagueView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
