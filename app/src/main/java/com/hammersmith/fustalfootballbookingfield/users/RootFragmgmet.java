package com.hammersmith.fustalfootballbookingfield.users;

import android.support.v4.app.Fragment;

import com.hammersmith.fustalfootballbookingfield.widget.BackPressImplement;
import com.hammersmith.fustalfootballbookingfield.widget.OnBackPressListener;

/**
 * Created by USER on 9/28/2015.
 */
public class RootFragmgmet extends Fragment implements OnBackPressListener {
    @Override
    public boolean onBackPress() {
        return new BackPressImplement(this).onBackPress();
    }
}