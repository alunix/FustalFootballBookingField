package com.hammersmith.fustalfootballbookingfield.owner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragment;

/**
 * Created by USER on 10/5/2015.
 */
public class Table_Booking extends RootFragment {
    TextView time7to8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_booking,container,false);
        time7to8 = (TextView)view.findViewById(R.id.txt7to8);
        bookSet();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        bookSet();
    }
    private void bookSet() {

        time7to8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time7to8.setBackgroundResource(R.drawable.bgborder_red);
            }
        });
    }
}
