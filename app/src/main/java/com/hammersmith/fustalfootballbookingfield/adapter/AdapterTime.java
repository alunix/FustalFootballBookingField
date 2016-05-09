package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.model.Time;
import java.util.List;

/**
 * Created by USER on 12/3/2015.
 */
public class AdapterTime extends RecyclerView.Adapter<AdapterTime.MyViewHolder> {
    private ClickListener clickListener;
    List<Time> times;
    Time time;
    Activity activity;
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView time;
        TextView available;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.timetitle);
            available = (TextView) itemView.findViewById(R.id.available);
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

    public AdapterTime(Activity activity, List<Time> times) {
        this.activity = activity;
        this.times = times;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_time, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        time = times.get(position);
        holder.time.setText(time.getTime());
//        holder.available.setBackgroundColor(R.color.background_completed_register);
        holder.available.setText(time.getBook());

    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
