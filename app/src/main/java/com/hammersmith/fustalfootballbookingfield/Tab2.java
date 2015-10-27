package com.hammersmith.fustalfootballbookingfield;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

public class Tab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tab2 = inflater.inflate(R.layout.tab_2, container, false);


        ImageView teamgroup = (ImageView)tab2.findViewById(R.id.imageShowTeam);
        teamgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new team_group();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.layoutTeam, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return tab2;
    }
}