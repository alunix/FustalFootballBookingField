package com.hammersmith.fustalfootballbookingfield.TabOwner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.widget.RootFragment;

/**
 * Created by USER on 11/4/2015.
 */
public class TabSetting extends RootFragment {
    Button addSmallField,removeSmallField,addMediumField,removeMediumField,addLargeField,removeLargeField;
    TextView numberSmallField,numberMediumField,numberLargeField;
    int countSmallField = 0;
    int countMediumField = 0;
    int countLargeField = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_setting,container,false);

        addSmallField = (Button) view.findViewById(R.id.btnAddSmall);
        removeSmallField = (Button) view.findViewById(R.id.btnRemoveSmall);
        addMediumField = (Button) view.findViewById(R.id.btnAddMedium);
        removeMediumField = (Button) view.findViewById(R.id.btnRemoveMedium);
        addLargeField = (Button) view.findViewById(R.id.btnAddLarge);
        removeLargeField = (Button) view.findViewById(R.id.btnRemoveLarge);

        numberSmallField = (TextView) view.findViewById(R.id.numberFieldSmall);
        numberMediumField = (TextView) view.findViewById(R.id.numberFieldMedium );
        numberLargeField = (TextView) view.findViewById(R.id.numberFieldLarge);

        addSmallField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countSmallField = countSmallField + 1;
                //Toast.makeText(getActivity(),"Number Small Field "+countSmallField,Toast.LENGTH_SHORT).show();
                numberSmallField.setText(String.valueOf(countSmallField));
            }
        });
        removeSmallField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countSmallField < 1){
                    Toast.makeText(getActivity(),"You have no field",Toast.LENGTH_SHORT).show();
                }
                else {
                    countSmallField = countSmallField - 1;
                    //Toast.makeText(getActivity(), "Number Small Field " + countSmallField, Toast.LENGTH_SHORT).show();
                    numberSmallField.setText(String.valueOf(countSmallField));
                }
            }
        });

        addMediumField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countMediumField = countMediumField + 1;
                //Toast.makeText(getActivity(),"Number Small Field "+countSmallField,Toast.LENGTH_SHORT).show();
                numberMediumField.setText(String.valueOf(countMediumField));
            }
        });
        removeMediumField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countMediumField < 1){
                    Toast.makeText(getActivity(),"You have no field",Toast.LENGTH_SHORT).show();
                }
                else {
                    countMediumField = countMediumField - 1;
                    //Toast.makeText(getActivity(), "Number Small Field " + countSmallField, Toast.LENGTH_SHORT).show();
                    numberMediumField.setText(String.valueOf(countMediumField));
                }
            }
        });

        addLargeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countLargeField = countLargeField + 1;
                //Toast.makeText(getActivity(),"Number Small Field "+countSmallField,Toast.LENGTH_SHORT).show();
                numberLargeField.setText(String.valueOf(countLargeField));
            }
        });
        removeLargeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countLargeField < 1) {
                    Toast.makeText(getActivity(), "You have no field", Toast.LENGTH_SHORT).show();
                } else {
                    countLargeField = countLargeField - 1;
                    //Toast.makeText(getActivity(), "Number Small Field " + countSmallField, Toast.LENGTH_SHORT).show();
                    numberLargeField.setText(String.valueOf(countLargeField));
                }
            }
        });

        return view;
    }
}
