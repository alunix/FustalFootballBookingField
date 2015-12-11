package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.AdapterLeague;

/**
 * Created by USER on 12/3/2015.
 */
public class TabLeague extends Fragment implements AdapterLeague.ClickListener {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    AdapterLeague mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_league_list,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recylcerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterLeague();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return view;
    }


    @Override
    public void itemClicked(View view, int position) {
//        Toast.makeText(getActivity(), "Item  "+position, Toast.LENGTH_SHORT).show();
        String url = "";
        if (position == 0){
            url = "https://www.ligabbva.com/liga-bbva/";
        }
        else if (position == 1){
            url = "http://www.premierleague.com";
        }
        else if (position == 2){
            url = "http://www.flashscore.com/soccer/germany/bundesliga/";
        }
        else if (position == 3){
            url = "http://www.legaseriea.it/it";
        }
        else if (position == 4){
            url = "http://www.uefa.com/uefachampionsleague/";
        }
        else if (position == 5){
            url = "http://www.football-league.co.uk/";
        }
        else {
            url = "http://www.cambodianfootball.com";
        }

        Fragment fragment = new TabLeagueView();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutLeague,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
