package com.hammersmith.fustalfootballbookingfield.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by USER on 9/25/2015.
 */
public class BackPressImplement implements OnBackPressListener {
    private final Fragment parentFragment;

    public BackPressImplement(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    public boolean onBackPress() {
        if(parentFragment == null)
            return false;
        int childCount = parentFragment.getChildFragmentManager().getBackStackEntryCount();
        if(childCount == 0){
            return false;
        }
        else{
            FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
            OnBackPressListener childFragment = (OnBackPressListener)childFragmentManager.getFragments().get(0);
            if(!childFragment.onBackPress()){
                childFragmentManager.popBackStackImmediate();
            }
            return true;
        }
    }
}
