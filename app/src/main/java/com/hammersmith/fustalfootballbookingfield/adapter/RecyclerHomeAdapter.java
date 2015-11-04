package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.*;
import android.widget.Toast;

import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.users.TabBooking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/2/2015.
 */
public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.MyViewHolder>{
    ClickListener clickListener;
    Context context;
    ContainerApplication main;
    public ArrayList<Integer> img = new ArrayList<>();
    public ArrayList<String> nameField = new ArrayList<>();
    public ArrayList<String> location = new ArrayList<>();
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.custom_cardview_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(root);

        return myViewHolder;
    }
    RecyclerHomeAdapter(){}

    public RecyclerHomeAdapter(Context context,ContainerApplication ma){
        img.add(R.drawable.imgnaga);
        img.add(R.drawable.imgdowntown);
        img.add(R.drawable.imgemperia);

        nameField.add("Imperial Stadium");
        nameField.add("Down Town Sport");
        nameField.add("Sport Club");

        location.add("Phnom Penh");
        location.add("Kompong Cham");
        location.add("Batdom Bong");

        this.context = context;
        main = ma;
    }

    @Override
    public void onBindViewHolder(RecyclerHomeAdapter.MyViewHolder holder, int position) {
        holder.imgField.setImageResource(img.get(position));
        holder.location.setText(location.get(position));
        holder.name.setText(nameField.get(position));
    }

    @Override
    public int getItemCount() {
        return img.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgField;
        TextView name,location;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameField);
            location = (TextView) itemView.findViewById(R.id.locationField);
            imgField = (ImageView)itemView.findViewById(R.id.cover);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            if (clickListener != null){
                clickListener.itemClicked(v, getLayoutPosition());
                fragmentJump();
                Toast.makeText(context,"Item "+getPosition(),Toast.LENGTH_SHORT).show();
                main.getSupportFragmentManager().beginTransaction().replace(R.id.layoutHome, new TabBooking()).addToBackStack(null).commit();

            }
        }
    }
    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public void switchContent(int id, Fragment fragment){
        if (context==null)
            return;
        if (context instanceof ContainerApplication){
            ContainerApplication application = (ContainerApplication) context;
            Fragment fgm = fragment;
            application.switchContent(id,fgm);
        }
    }
    public void fragmentJump(){
        Fragment fragment = new TabBooking();
        Bundle bundle = new Bundle();
        bundle.putString("Key","value");
        fragment.setArguments(bundle);
        switchContent(R.id.layoutHome, fragment);
    }
}
