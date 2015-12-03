package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.TabLeague;
import com.hammersmith.fustalfootballbookingfield.model.FieldDetail;
import com.hammersmith.fustalfootballbookingfield.model.League;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2015.
 */
public class AdapterLeague extends RecyclerView.Adapter<AdapterLeague.MyViewHolder>{
    private ClickListener clickListener;
    List<League> leagues;

    public AdapterLeague(){
        super();
        leagues = new ArrayList<League>();
        League league = new League();
        league.setImage(R.drawable.logofield);
        leagues.add(league);

        league = new League();
        league.setImage(R.drawable.league);
        leagues.add(league);

        league = new League();
        league.setImage(R.drawable.germanyleague);
        leagues.add(league);

        league = new League();
        league.setImage(R.drawable.italyleague);
        leagues.add(league);

        league = new League();
        league.setImage(R.drawable.championleague);
        leagues.add(league);

        league = new League();
        league.setImage(R.drawable.footballleaguejpg);
        leagues.add(league);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_league, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        League league = leagues.get(position);
        holder.imageView.setImageResource(league.getImage());

    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageLeague);
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
}
