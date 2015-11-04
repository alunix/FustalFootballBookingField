package com.hammersmith.fustalfootballbookingfield.preivew;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabBooking.MainTabBooking;
import com.hammersmith.fustalfootballbookingfield.users.RootFragmgmet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Booking extends RootFragmgmet {
    Button button;
    ImageView date,time,ball;
    public static TextView textDate,textTime,textField;
    DateFragment dateFragment;
    TimePicker timePicker;
    String value = "";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_booking,container,false);

        textField = (TextView)view.findViewById(R.id.txtfield);

        textTime = (TextView)view.findViewById(R.id.txttime);
        time = (ImageView) view.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Time_Selected();
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getFragmentManager(),"time");
                //selectTime();
//                TimeSelected timeSelected = new TimeSelected();
//                timeSelected.show(getFragmentManager(),"Time Selected");
            }
        });
        textDate = (TextView)view.findViewById(R.id.txtday);
        date = (ImageView)view.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment picker = new DateFragment();
                picker.show(getFragmentManager(), "Choose Date");

            }
        });

        ball = (ImageView)view.findViewById(R.id.field);
        ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectField();

            }
        });

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
    private void selectField() {
        Dialog dialog;
        final int[] mSelected = {-1};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Field");
        builder.setCancelable(true);
        final String[] fields = new String[]{"Small","Medium","Large"};
        final DialogInterface.OnMultiChoiceClickListener onClick =
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked){
                            if((mSelected[0] != -1) && (mSelected[0] != which)){
                                final int oldVal = mSelected[0];
                                final AlertDialog alert = (AlertDialog)dialog;
                                final ListView list = alert.getListView();
                                list.setItemChecked(oldVal,false);
                            }
                            mSelected[0] = which;
                        }else
                            mSelected[0] = -1;
                    }
                };
        builder.setMultiChoiceItems(fields,null,onClick);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = null;
                if(mSelected[0] == -1)
                    message = "You didn't select!";
                else
                    message = fields[mSelected[0]];
                textField.setText(message);
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
    private void Time_Selected(){
        Dialog dialog;
        final int[] mSelected = {-1};
//        @Override
//        protected void onResume() {
        super.onResume();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Time");
        builder.setCancelable(true);
        final String[] strings = new String[]{"7 To 8 AM","8 To 9 AM","9 To 10 AM","10 To 11 AM","11 To 12 AM","12 To 1 PM",
                "1 To 2 PM","2 To 3 PM","3 To 4 PM","4 To 5 PM","5 To 6 PM","6 To 7 PM","7 To 8 PM","8 To 9 PM","9 To 10 PM"};
        final DialogInterface.OnMultiChoiceClickListener onClick =
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override public void onClick(final DialogInterface dialog,
                                                  final int which, final boolean isChecked) {
                        if (isChecked) {
                            if ((mSelected[0] != -1) && (mSelected[0] != which)) {
                                final int oldVal = mSelected[0];
                                final AlertDialog alert = (AlertDialog)dialog;
                                final ListView list = alert.getListView();
                                list.setItemChecked(oldVal, false);
                            }
                            mSelected[0] = which;
                        } else
                            mSelected[0] = -1;
                    }
                };
        builder.setMultiChoiceItems(strings, null, onClick);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override public void onClick(final DialogInterface dialog,
                                          final int which) {
                String message = null;
                if (mSelected[0] == -1)
                    message = "You didn't select!";
                else
                    message = strings[mSelected[0]];
                //Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                textTime.setText(message);
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
    //}




    private void selectTime() {
        final Dialog dialogFragment;
        final String[] items = {"Time 7 To 8 AM","Time 8 To 9 AM","Time 9 To 10 AM","Time 10 To 11 AM","Time 11 To 12 AM","Time 12 To 1 PM",
                "Time 1 To 2 PM","Time 2 To 3 PM","Time 3 To 4 PM","Time 4 To 5 PM","Time 5 To 6 PM","Time 6 To 7 PM",
                "TIme 7 To 8 PM","Time 8 To 9 PM","Time 9 To 10 PM"};
        final int numArray = items.length;
        final ArrayList<Integer> itemSelected = new ArrayList<Integer>();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Time");

        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                if(isSelected){
                    itemSelected.add(selectedItemId);
                }
                else if(itemSelected.contains(selectedItemId)){
                    itemSelected.remove(Integer.valueOf(selectedItemId));
                }
            }
        }).setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedIndex = "";
                for (Integer i : itemSelected) {
                    selectedIndex += i + " , ";
                }
                //Toast.makeText(getActivity(),"Item Selected : "+selectedIndex,Toast.LENGTH_SHORT).show();
                textTime.setText("Selected " + selectedIndex);
//                for (String value : items) {
//
//                    //selectedIndex += i + ", ";
//                    Toast.makeText(getActivity(), "Item Selected : " + value , Toast.LENGTH_LONG).show();
//                }

                //Toast.makeText(getApplicationContext(),"Item Selected :" + items[which],Toast.LENGTH_LONG).show();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getContext(),items[which],Toast.LENGTH_LONG).show();
                dialog.cancel();

            }
        });
        dialogFragment = builder.create();
        dialogFragment.show();
    }

    public static class DateFragment extends DialogFragment implements DatePickerDialog.OnCancelListener, DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            Calendar c = Calendar.getInstance();
            c.set(year,month,day);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            String formattedDate = sdf.format(c.getTime());
            textDate.setText(day + " / " + (month + 1) + " / " + year);


        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(),this,hours,minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            textTime.setText(hourOfDay + " : " + minute);
        }
    }

}
