package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.model.History;
import com.hammersmith.fustalfootballbookingfield.model.League;

import java.util.List;

/**
 * Created by Thuon on 2/26/2016.
 */
public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder>  {
    private ClickListener clickListener;
    List<History> histories;
    Context context;
    History history;
    Activity activity;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView location,type,field,price,date,time,name,no;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name);
            location = (TextView) itemView.findViewById(R.id.location);
            type = (TextView) itemView.findViewById(R.id.type);
            field = (TextView) itemView.findViewById(R.id.field);
//            price = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            no = (TextView) itemView.findViewById(R.id.no);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getLayoutPosition());
            }
        }
    }

    public AdapterHistory(Activity activity, List<History> histories) {
        this.activity = activity;
        this.histories = histories;
    }
    @Override
    public AdapterHistory.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_history, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterHistory.MyViewHolder holder, int position) {
        history = histories.get(position);
        holder.no.setText(history.getNo());
        holder.name.setText(history.getUsername());
        holder.location.setText(history.getLocation());
        holder.type.setText(history.getType());
        holder.field.setText(history.getField());
        holder.date.setText(history.getDate());
        holder.time.setText(history.getTime());
//        holder.price.setText(history.getPrice());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
