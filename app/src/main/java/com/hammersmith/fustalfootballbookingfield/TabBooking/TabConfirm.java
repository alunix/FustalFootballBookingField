package com.hammersmith.fustalfootballbookingfield.TabBooking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.RootFragmgmet;

/**
 * Created by USER on 10/1/2015.
 */
public class TabConfirm extends RootFragmgmet {
    Button confirm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirm,container,false);

        confirm = (Button) view.findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Your message was reply to customer!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
