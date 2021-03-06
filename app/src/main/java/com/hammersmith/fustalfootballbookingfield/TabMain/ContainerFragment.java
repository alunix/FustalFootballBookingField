package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.ViewPagerAdapter;
import com.hammersmith.fustalfootballbookingfield.widget.SlidingTabLayoutText;


public class ContainerFragment extends Fragment {
    Context context;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayoutText tabs;
    CharSequence Titles[] = {"Home", "Map", "League"};
    int Numboftabs = 3;

    public ContainerFragment() {
    }

    public ContainerFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.container_fragement, container, false);
        setupParent(view);
        pager = (ViewPager) view.findViewById(R.id.pager);
        tabs = (SlidingTabLayoutText) view.findViewById(R.id.tabs);
        tabs.setCustomTabView(R.layout.custom_tab_text, R.id.textCustomTab);
        tabs.setDistributeEvenly(true);
        tabs.setSelected(true);
        tabs.setSelectedIndicatorColors(getResources().getColor(R.color.selectors));
        tabs.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabColorTest);
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ViewPagerAdapter(getChildFragmentManager(), Titles, Numboftabs);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    protected void setupParent(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupParent(innerView);
            }
        }
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}