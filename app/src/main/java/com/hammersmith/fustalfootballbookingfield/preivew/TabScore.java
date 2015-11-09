package com.hammersmith.fustalfootballbookingfield.preivew;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;
import com.hammersmith.fustalfootballbookingfield.widget.ExpandableListAdaptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 9/18/2015.
 */
public class TabScore extends RootFragment {

    ExpandableListAdaptor listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public TabScore(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View score = inflater.inflate(R.layout.tab_score,container,false);

        expListView = (ExpandableListView)score.findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdaptor(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        return score;
    }

    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String,List<String>>();

        listDataHeader.add("All Session");
        listDataHeader.add("All Club");

        List<String> matchWeek = new ArrayList<String>();
        matchWeek.add("All Session");
        matchWeek.add("Match week 1");
        matchWeek.add("Match week 2");
        matchWeek.add("Match week 3");
        matchWeek.add("Match week 4");
        matchWeek.add("Match week 5");
        matchWeek.add("Match week 6");
        matchWeek.add("Match week 7");
        matchWeek.add("Match week 8");
        matchWeek.add("Match week 9");
        matchWeek.add("Match week 10");
        matchWeek.add("Match week 11");
        matchWeek.add("Match week 12");
        matchWeek.add("Match week 13");
        matchWeek.add("Match week 14");
        matchWeek.add("Match week 15");
        matchWeek.add("Match week 16");
        matchWeek.add("Match week 17");
        matchWeek.add("Match week 18");
        matchWeek.add("Match week 19");
        matchWeek.add("Match week 20");
        matchWeek.add("Match week 21");
        matchWeek.add("Match week 22");
        matchWeek.add("Match week 23");
        matchWeek.add("Match week 24");
        matchWeek.add("Match week 25");
        matchWeek.add("Match week 26");
        matchWeek.add("Match week 27");
        matchWeek.add("Match week 28");
        matchWeek.add("Match week 29");
        matchWeek.add("Match week 30");
        matchWeek.add("Match week 31");
        matchWeek.add("Match week 32");
        matchWeek.add("Match week 33");
        matchWeek.add("Match week 34");
        matchWeek.add("Match week 35");
        matchWeek.add("Match week 36");
        matchWeek.add("Match week 37");
        matchWeek.add("Match week 38");

        List<String> allclub = new ArrayList<String>();
        allclub.add("All Club");
        allclub.add("Arsenal");
        allclub.add("Aston Villa");
        allclub.add("Bournemouth");
        allclub.add("Chelsea");
        allclub.add("Chrystal Palace");
        allclub.add("Everton");
        allclub.add("Leicester");
        allclub.add("Liverpool");
        allclub.add("Man City");
        allclub.add("Man Unt");
        allclub.add("New Castle");
        allclub.add("Norwich");
        allclub.add("Southampton");
        allclub.add("Stoke");
        allclub.add("Sunderland");
        allclub.add("Swansea");
        allclub.add("Spurs");
        allclub.add("Watford");
        allclub.add("West Brom");
        allclub.add("West Ham");

        listDataChild.put(listDataHeader.get(0),matchWeek);
        listDataChild.put(listDataHeader.get(1),allclub);

    }
}
