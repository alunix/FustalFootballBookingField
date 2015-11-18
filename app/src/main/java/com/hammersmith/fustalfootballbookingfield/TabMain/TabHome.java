package com.hammersmith.fustalfootballbookingfield.TabMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.Activities.ActivityBooking;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RecyclerHomeAdapter;

/**
 * Created by USER on 10/29/2015.
 */
public class TabHome extends Fragment implements RecyclerHomeAdapter.ClickListener{
    RecyclerView recyclerView;
    RecyclerHomeAdapter adapter;
    View root;
    ImageView imageView;
    TextView bookingField;

    String[] title = {
            "Imperial Stadium",
            "Down Town Sport",
            "Sport Club"
    };
    int[] field = {
            R.drawable.imgnaga,
            R.drawable.imgdowntown,
            R.drawable.imgemperia
    };
    public TabHome(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.tab_home, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recylcerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerHomeAdapter(getActivity(),new ContainerApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setClickListener(this);

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void itemClicked(View view, int position) {

        //Toast.makeText(getActivity(), "Click Item" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ActivityBooking.class);
        intent.putExtra("title",title[position]);
        Bundle bundle = new Bundle();

        bundle.putInt("field",field[position]);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}