package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabLeague;

public class FragmentBooking extends Fragment {
    Button button;
    String dateBooking;
    String date;
    TextView title;
    String fields, titles, days;
    public static TextView textDate, textTime, textField;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        textDate = (TextView) view.findViewById(R.id.txtday);
        textField = (TextView) view.findViewById(R.id.txtfield);
        textTime = (TextView) view.findViewById(R.id.txttime);
        title = (TextView) view.findViewById(R.id.title);

        String day = getArguments().getString("dateBooking");

        final String time = getArguments().getString("timeBooking");
        textTime.setText(time);

        String date = getArguments().getString("date");
        textDate.setText(date);

        String title = getArguments().getString("title");
        textField.setText(title);
        titles = title;

        String field = getArguments().getString("field");
        fields = field;

        button = (Button) view.findViewById(R.id.btnBooking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Booking successful!", Toast.LENGTH_SHORT).show();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentTimeBooking();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", titles);
                    bundle.putString("field", fields);
                    bundle.putString("dateBooking", days);
                    bundle.putString("timeBooking",time);
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.layoutBooking, fragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        return view;
    }

}
