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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by USER on 11/5/2015.
 */
public class FragmentTimeBooking extends Fragment {
//    CharSequence[] time =  {"Time 6 To 7 AM",
//                            "Time 7 To 8 AM",
//                            "Time 8 To 9 AM",
//                            "Time 9 To 10 AM"};
//    int count = time.length;

    TextView time6_7am, time7_8am, time8_9am, time9_10am, time10_11am, time11_12pm, time12_1pm, time1_2pm, time2_3pm, time3_4pm, time4_5pm, time5_6pm, time6_7pm, time7_8pm, time8_9pm, time9_10pm;
    TextView dayBooking;
    Button buttonBooking;
    String time6to7AM = "6 - 7, ";
    String time7to8AM = "7 - 8, ";
    String time8to9AM = "8 - 9, ";
    String time9to10AM = "9 - 10, ";
    String time10to11AM = "10 - 11, ";
    String time11to12PM = "11 - 12, ";
    String time12to1PM = "12 - 13, ";
    String time1to2PM = "13 - 14, ";
    String time2to3PM = "14 - 15, ";
    String time3to4PM = "15 - 16, ";
    String time4to5PM = "16 - 17, ";
    String time5to6PM = "17 - 18, ";
    String time6to7PM = "18 - 19, ";
    String time7to8PM = "19 - 20, ";
    String time8to9PM = "20 - 21, ";
    String time9to10PM = "21 - 22, ";

    String strTime = "";
    String str6am = "";
    String str7am = "";
    String str8am = "";
    String str9am = "";
    String str10am = "";
    String str11am = "";
    String str12pm = "";
    String str1pm = "";
    String str2pm = "";
    String str3pm = "";
    String str4pm = "";
    String str5pm = "";
    String str6pm = "";
    String str7pm = "";
    String str8pm = "";
    String str9pm = "";

    CheckBox ch6am, ch7am, ch8am, ch9am, ch10am, ch11am, ch12pm, ch1pm, ch2pm, ch3pm, ch4pm, ch5pm, ch6pm, ch7pm, ch8pm, ch9pm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_booking, container, false);


        ch6am = (CheckBox) view.findViewById(R.id.ch6am);
        ch6am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time6_7am.setText("Not Available");
                    time6_7am.setBackgroundResource(R.color.bgBooking);
                } else {
                    time6_7am.setText("Available Booking");
                    time6_7am.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch7am = (CheckBox) view.findViewById(R.id.ch7am);
        ch7am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time7_8am.setText("Not Available");
                    time7_8am.setBackgroundResource(R.color.bgBooking);
                } else {
                    time7_8am.setText("Available Booking");
                    time7_8am.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch8am = (CheckBox) view.findViewById(R.id.ch8am);
        ch8am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time8_9am.setText("Not Available");
                    time8_9am.setBackgroundResource(R.color.bgBooking);
                } else {
                    time8_9am.setText("Available Booking");
                    time8_9am.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch9am = (CheckBox) view.findViewById(R.id.ch9am);
        ch9am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time9_10am.setText("Not Available");
                    time9_10am.setBackgroundResource(R.color.bgBooking);
                } else {
                    time9_10am.setText("Available Booking");
                    time9_10am.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch10am = (CheckBox) view.findViewById(R.id.ch10am);
        ch10am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time10_11am.setText("Not Available");
                    time10_11am.setBackgroundResource(R.color.bgBooking);
                } else {
                    time10_11am.setText("Available Booking");
                    time10_11am.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch11am = (CheckBox) view.findViewById(R.id.ch11am);
        ch11am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time11_12pm.setText("Not Available");
                    time11_12pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time11_12pm.setText("Available Booking");
                    time11_12pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch12pm = (CheckBox) view.findViewById(R.id.ch12pm);
        ch12pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time12_1pm.setText("Not Available");
                    time12_1pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time12_1pm.setText("Available Booking");
                    time12_1pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch1pm = (CheckBox) view.findViewById(R.id.ch1pm);
        ch1pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time1_2pm.setText("Not Available");
                    time1_2pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time1_2pm.setText("Available Booking");
                    time1_2pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch2pm = (CheckBox) view.findViewById(R.id.ch2pm);
        ch2pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time2_3pm.setText("Not Available");
                    time2_3pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time2_3pm.setText("Available Booking");
                    time2_3pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch3pm = (CheckBox) view.findViewById(R.id.ch3pm);
        ch3pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time3_4pm.setText("Not Available");
                    time3_4pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time3_4pm.setText("Available Booking");
                    time3_4pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch4pm = (CheckBox) view.findViewById(R.id.ch4pm);
        ch4pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time4_5pm.setText("Not Available");
                    time4_5pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time4_5pm.setText("Available Booking");
                    time4_5pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch5pm = (CheckBox) view.findViewById(R.id.ch5pm);
        ch5pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time5_6pm.setText("Not Available");
                    time5_6pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time5_6pm.setText("Available Booking");
                    time5_6pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch6pm = (CheckBox) view.findViewById(R.id.ch6pm);
        ch6pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time6_7pm.setText("Not Available");
                    time6_7pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time6_7pm.setText("Available Booking");
                    time6_7pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch7pm = (CheckBox) view.findViewById(R.id.ch7pm);
        ch7pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time7_8pm.setText("Not Available");
                    time7_8pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time7_8pm.setText("Available Booking");
                    time7_8pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch8pm = (CheckBox) view.findViewById(R.id.ch8pm);
        ch8pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time8_9pm.setText("Not Available");
                    time8_9pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time8_9pm.setText("Available Booking");
                    time8_9pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });
        ch9pm = (CheckBox) view.findViewById(R.id.ch9pm);
        ch9pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    time9_10pm.setText("Not Available");
                    time9_10pm.setBackgroundResource(R.color.bgBooking);
                } else {
                    time9_10pm.setText("Available Booking");
                    time9_10pm.setBackgroundResource(R.color.bgUnChecked);
                }
            }
        });

        time6_7am = (TextView) view.findViewById(R.id.time6to7am);
        time7_8am = (TextView) view.findViewById(R.id.time7to8am);
        time8_9am = (TextView) view.findViewById(R.id.time8to9am);
        time9_10am = (TextView) view.findViewById(R.id.time9to10am);
        time10_11am = (TextView) view.findViewById(R.id.time10to11am);
        time11_12pm = (TextView) view.findViewById(R.id.time11to12pm);
        time12_1pm = (TextView) view.findViewById(R.id.time12to1pm);
        time1_2pm = (TextView) view.findViewById(R.id.time1to2pm);
        time2_3pm = (TextView) view.findViewById(R.id.time2to3pm);
        time3_4pm = (TextView) view.findViewById(R.id.time3to4pm);
        time4_5pm = (TextView) view.findViewById(R.id.time4to5pm);
        time5_6pm = (TextView) view.findViewById(R.id.time5to6pm);
        time6_7pm = (TextView) view.findViewById(R.id.time6to7pm);
        time7_8pm = (TextView) view.findViewById(R.id.time7to8pm);
        time8_9pm = (TextView) view.findViewById(R.id.time8to9pm);
        time9_10pm = (TextView) view.findViewById(R.id.time9to10pm);

        dayBooking = (TextView) view.findViewById(R.id.dayBooking);
        final String day = getArguments().getString("dateBooking");
        dayBooking.setText(day);
        final String title = getArguments().getString("title");
        final String field = getArguments().getString("field");

        buttonBooking = (Button) view.findViewById(R.id.btnBooking);
        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox();
                strTime = str6am + str7am + str8am + str9am + str10am + str11am + str12pm + str1pm
                        + str2pm + str3pm + str4pm + str5pm + str6pm + str7pm + str8pm + str9pm;
                Fragment fragment = new FragmentBooking();
                Bundle bundle = new Bundle();
                bundle.putString("timeBooking", strTime);
                bundle.putString("date", day);
                bundle.putString("title", title);
                bundle.putString("field", field);
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.layoutTimeBooking, fragment).commit();
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment fragment = new FragmentCustomCalendar();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title);
                    bundle.putString("field", field);
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.layoutTimeBooking, fragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return view;
    }

    public void checkBox() {
        if (ch6am.isChecked()) {
            str6am = time6to7AM;
        }
        if (ch7am.isChecked()) {
            str7am = time7to8AM;
        }
        if (ch8am.isChecked()) {
            str8am = time8to9AM;
        }
        if (ch9am.isChecked()) {
            str9am = time9to10AM;
        }
        if (ch10am.isChecked()) {
            str10am = time10to11AM;
        }
        if (ch11am.isChecked()) {
            str11am = time11to12PM;
        }
        if (ch12pm.isChecked()) {
            str12pm = time12to1PM;
        }
        if (ch1pm.isChecked()) {
            str1pm = time1to2PM;
        }
        if (ch2pm.isChecked()) {
            str2pm = time2to3PM;
        }
        if (ch3pm.isChecked()) {
            str3pm = time3to4PM;
        }
        if (ch4pm.isChecked()) {
            str4pm = time4to5PM;
        }
        if (ch5pm.isChecked()) {
            str5pm = time5to6PM;
        }
        if (ch6pm.isChecked()) {
            str6pm = time6to7PM;
        }
        if (ch7pm.isChecked()) {
            str7pm = time7to8PM;
        }
        if (ch8pm.isChecked()) {
            str8pm = time8to9PM;
        }
        if (ch9pm.isChecked()) {
            str9pm = time9to10PM;
        }
    }
}
