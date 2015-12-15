package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.Fragments.FragmentTimeBooking;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.model.Time;

import java.util.List;

/**
 * Created by USER on 12/15/2015.
 */
public class RecyclerAdapterTime extends RecyclerView.Adapter<RecyclerAdapterTime.MyViewHolder> {
    private ClickListener clickListener;
    Activity activity;
    private List<Time> times;
    Time time;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView timeBooking;
        public MyViewHolder(View itemView) {
            super(itemView);
            timeBooking = (TextView) itemView.findViewById(R.id.timeBooking);
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

    public RecyclerAdapterTime(FragmentActivity activity, List<Time> times) {
        this.activity = activity;
        this.times = times;

    }
    @Override
    public RecyclerAdapterTime.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_time_booking, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        time = times.get(position);
        holder.timeBooking.setText(time.getTime());

    }

    @Override
    public int getItemCount() {
        return times.size();
    }
    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

}
