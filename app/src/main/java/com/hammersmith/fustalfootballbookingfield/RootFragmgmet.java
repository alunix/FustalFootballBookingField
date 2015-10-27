package com.hammersmith.fustalfootballbookingfield;

import android.support.v4.app.Fragment;

/**
 * Created by USER on 9/28/2015.
 */
public class RootFragmgmet extends Fragment implements OnBackPressListener {
    @Override
    public boolean onBackPress() {
        return new BackPressImplement(this).onBackPress();
    }
}
