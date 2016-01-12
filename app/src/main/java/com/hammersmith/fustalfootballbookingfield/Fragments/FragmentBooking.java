package com.hammersmith.fustalfootballbookingfield.Fragments;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabLeague;

public class FragmentBooking extends Fragment {
    Button button;
    String dateBooking;
    String date;
    TextView title;
    EditText name,phone,email;
    String fields, catField, days;
    public static TextView textDate, textTime, textField;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking_field, container, false);

        name = (EditText) view.findViewById(R.id.name);
        phone = (EditText) view.findViewById(R.id.phone);
        email = (EditText) view.findViewById(R.id.email);
        textDate = (TextView) view.findViewById(R.id.txtday);
        textField = (TextView) view.findViewById(R.id.txtfield);
        textTime = (TextView) view.findViewById(R.id.txttime);
        title = (TextView) view.findViewById(R.id.title);

        String day = getArguments().getString("dateBooking");
        final String time = getArguments().getString("timeBooking");
        textTime.setText(time);
        String date = getArguments().getString("date");
        days = date;
        textDate.setText(date);
        String title = getArguments().getString("title");
        textField.setText(title);
        catField = title;
        String field = getArguments().getString("field");
        fields = field;
        final String dayBooking = getArguments().getString("dayBooking");
        final int id = getArguments().getInt("ID");
        button = (Button) view.findViewById(R.id.btnBooking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Booking successful!", Toast.LENGTH_SHORT).show();
                name.setText("");
                phone.setText("");
                email.setText("");
                textDate.setText("");
                textField.setText("");
                textTime.setText("");
                name.clearFocus();
                phone.clearFocus();
                email.clearFocus();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentTime();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", catField);
                    bundle.putString("field", fields);
                    bundle.putString("dateBooking", days);
                    bundle.putString("timeBooking",time);
                    bundle.putString("dayBooking", dayBooking);
                    bundle.putInt("ID", id);
                    fragment.setArguments(bundle);

                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                    transaction.replace(R.id.layoutBooking, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
