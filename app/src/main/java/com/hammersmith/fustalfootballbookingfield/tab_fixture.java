package com.hammersmith.fustalfootballbookingfield;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 9/18/2015.
 */
public class tab_fixture extends RootFragmgmet {

    ExpandableListAdaptor listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    String value = "";
    public tab_fixture(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View fixture = inflater.inflate(R.layout.tab_fixture,container,false);

        expListView = (ExpandableListView)fixture.findViewById(R.id.lvExp);
        prepareListData();
        listAdapter = new ExpandableListAdaptor(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

//                Toast.makeText(getContext(), "Group Clicked: " + listDataHeader.get(groupPosition)
//                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getContext(), listDataHeader.get(groupPosition)
//                        + " Expanable", Toast.LENGTH_SHORT).show();
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

//                Toast.makeText(getContext(), listDataHeader.get(groupPosition)
//                        + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                TextView showMatchWeek = (TextView)getActivity().findViewById(R.id.lblListHeader);
                TextView showAllClub = (TextView)getActivity().findViewById(R.id.lblListHeader);

                showAllClub.setText(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                showMatchWeek.setText(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));

//                Toast.makeText(getContext(),listDataChild.get(listDataHeader.get(groupPosition))
//                .get(childPosition),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return fixture;
    }


    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String,List<String>>();

        listDataHeader.add("All Session");
        listDataHeader.add("All Club");

        List<String> matchWeek = new ArrayList<String>();
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
