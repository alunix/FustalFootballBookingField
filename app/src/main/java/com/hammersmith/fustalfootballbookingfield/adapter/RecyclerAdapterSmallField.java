package com.hammersmith.fustalfootballbookingfield.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.Fragments.FragmentCalendarBooking;
import com.hammersmith.fustalfootballbookingfield.R;

import java.util.ArrayList;

/**
 * Created by USER on 11/17/2015.
 */
public class RecyclerAdapterSmallField extends RecyclerView.Adapter<RecyclerAdapterSmallField.MyViewHolder> {
    ContainerApplication main;
    ClickListener clickListener;
    Context context;
    int i;
    String strField = "";
    public ArrayList<String> dayMonFri = new ArrayList<>();
    public ArrayList<String> daySatSun = new ArrayList<>();
    public ArrayList<String> timeMonFriMorn = new ArrayList<>();
    public ArrayList<String> timeMonFriEven = new ArrayList<>();
    public ArrayList<String> timeSatSunMorn = new ArrayList<>();
    public ArrayList<String> timeSatSunEven = new ArrayList<>();
    public ArrayList<String> priceMonFriMorn = new ArrayList<>();
    public ArrayList<String> priceMonFriEven = new ArrayList<>();
    public ArrayList<String> priceSatSunMorn = new ArrayList<>();
    public ArrayList<String> priceSatSunEven = new ArrayList<>();


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.custom_cardview_small_field,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(root);
        return myViewHolder;
    }
    public RecyclerAdapterSmallField(){

    }
    public RecyclerAdapterSmallField(Context context,ContainerApplication ma){
        dayMonFri.add("Monday - Friday");
        dayMonFri.add("Monday - Friday");
        dayMonFri.add("Monday - Friday");
        dayMonFri.add("Monday - Friday");
        dayMonFri.add("Monday - Friday");
        dayMonFri.add("Monday - Friday");

        daySatSun.add("Saturday - Sunday");
        daySatSun.add("Saturday - Sunday");
        daySatSun.add("Saturday - Sunday");
        daySatSun.add("Saturday - Sunday");
        daySatSun.add("Saturday - Sunday");
        daySatSun.add("Saturday - Sunday");

        timeMonFriMorn.add("6 - 14");
        timeMonFriMorn.add("6 - 14");
        timeMonFriMorn.add("6 - 14");
        timeMonFriMorn.add("6 - 14");
        timeMonFriMorn.add("6 - 14");
        timeMonFriMorn.add("6 - 14");

        timeMonFriEven.add("14 - 22");
        timeMonFriEven.add("14 - 22");
        timeMonFriEven.add("14 - 22");
        timeMonFriEven.add("14 - 22");
        timeMonFriEven.add("14 - 22");
        timeMonFriEven.add("14 - 22");

        timeSatSunMorn.add("6 - 14");
        timeSatSunMorn.add("6 - 14");
        timeSatSunMorn.add("6 - 14");
        timeSatSunMorn.add("6 - 14");
        timeSatSunMorn.add("6 - 14");
        timeSatSunMorn.add("6 - 14");

        timeSatSunEven.add("14 - 22");
        timeSatSunEven.add("14 - 22");
        timeSatSunEven.add("14 - 22");
        timeSatSunEven.add("14 - 22");
        timeSatSunEven.add("14 - 22");
        timeSatSunEven.add("14 - 22");

        priceMonFriMorn.add("$5");
        priceMonFriMorn.add("$5");
        priceMonFriMorn.add("$5");
        priceMonFriMorn.add("$5");
        priceMonFriMorn.add("$5");
        priceMonFriMorn.add("$5");

        priceMonFriEven.add("$10");
        priceMonFriEven.add("$10");
        priceMonFriEven.add("$10");
        priceMonFriEven.add("$10");
        priceMonFriEven.add("$10");
        priceMonFriEven.add("$10");

        priceSatSunMorn.add("$8");
        priceSatSunMorn.add("$8");
        priceSatSunMorn.add("$8");
        priceSatSunMorn.add("$8");
        priceSatSunMorn.add("$8");
        priceSatSunMorn.add("$8");

        priceSatSunEven.add("$15");
        priceSatSunEven.add("$15");
        priceSatSunEven.add("$15");
        priceSatSunEven.add("$15");
        priceSatSunEven.add("$15");
        priceSatSunEven.add("$15");

        this.context = context;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.dayMonFri.setText(dayMonFri.get(position));
        holder.daySatSun.setText(daySatSun.get(position));
        holder.timeMonFriMorn.setText(timeMonFriMorn.get(position));
        holder.timeMonFriEven.setText(timeMonFriEven.get(position));
        holder.timeSatSunMorn.setText(timeSatSunMorn.get(position));
        holder.timeSatSunEven.setText(timeSatSunEven.get(position));
        holder.priceMonFriMorn.setText(priceMonFriMorn.get(position));
        holder.priceMonFriEven.setText(priceMonFriEven.get(position));
        holder.priceSatSunMorn.setText(priceSatSunMorn.get(position));
        holder.priceSatSunEven.setText(priceSatSunEven.get(position));

        for (i=0; i<=position+1; i++){
            strField = String.valueOf(i);
        }
        holder.numField.setText(strField);

//        holder.buttonBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {


//                Fragment fragment = new FragmentCalendarBooking();
//                FragmentManager fragmentManager = getChildFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.layoutSmall,fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dayMonFri,daySatSun,timeMonFriMorn,timeMonFriEven,timeSatSunMorn,timeSatSunEven,priceMonFriMorn,priceMonFriEven,priceSatSunMorn,priceSatSunEven;
        TextView buttonBooking;
        TextView numField;

        public MyViewHolder(View itemView) {
            super(itemView);
            dayMonFri = (TextView) itemView.findViewById(R.id.dayMonFri);
            daySatSun = (TextView) itemView.findViewById(R.id.daySatSun);
            timeMonFriMorn = (TextView) itemView.findViewById(R.id.timeMonFriMorn);
            timeMonFriEven = (TextView) itemView.findViewById(R.id.timeMonFriEven);
            timeSatSunMorn = (TextView) itemView.findViewById(R.id.timeSatSunMorn);
            timeSatSunEven = (TextView) itemView.findViewById(R.id.timeSatSunEven);
            priceMonFriMorn = (TextView) itemView.findViewById(R.id.priceMonFriMorn);
            priceMonFriEven = (TextView) itemView.findViewById(R.id.priceMonFriEven);
            priceSatSunMorn = (TextView) itemView.findViewById(R.id.priceSatSunMorn);
            priceSatSunEven = (TextView) itemView.findViewById(R.id.priceSatSunEven);
            //buttonBooking = (TextView) itemView.findViewById(R.id.btnBookingField);
            numField = (TextView) itemView.findViewById(R.id.numField);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }
        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.itemClicked(v, getLayoutPosition());

            }
        }
    }

    @Override
    public int getItemCount() {
        return dayMonFri.size();
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
}
