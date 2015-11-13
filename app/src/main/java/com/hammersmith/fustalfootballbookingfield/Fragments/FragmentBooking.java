package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabOwner.MainTabBooking;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;

public class FragmentBooking extends RootFragment {
    Button button;
    String dateBooking;
    String date;
    TextView title;
    public static TextView textDate,textTime,textField;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking,container,false);

        //Typeface fontFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Black.ttf");
        textDate = (TextView) view.findViewById(R.id.txtday);
        textField = (TextView)view.findViewById(R.id.txtfield);
        textTime = (TextView)view.findViewById(R.id.txttime);
        title = (TextView) view.findViewById(R.id.title);
       // title.setTypeface(fontFace);

//        String field = getArguments().getString("field");
//        textField.setText(field);

        String time = getArguments().getString("timeBooking");
        textTime.setText(time);

        String date = getArguments().getString("date");
        textDate.setText(date);

        String field = getArguments().getString("field");
        textField.setText(field);

        button = (Button)view.findViewById(R.id.btnBooking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MainTabBooking();
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.layoutBooking, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
