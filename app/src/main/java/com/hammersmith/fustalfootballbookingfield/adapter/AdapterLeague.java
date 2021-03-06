package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.League;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 12/3/2015.
 */
public class AdapterLeague extends RecyclerView.Adapter<AdapterLeague.MyViewHolder> {
    private ClickListener clickListener;
    List<League> leagues;
    Context context;
    League league;
    Activity activity;
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageLeague;
        TextView title;
        CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            imageLeague = (ImageView) itemView.findViewById(R.id.imageLeague);
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

    public AdapterLeague(Activity activity, List<League> leagues) {
        this.activity = activity;
        this.leagues = leagues;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_league, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            league = leagues.get(position);
        Uri uri = Uri.parse(league.getImage());
        context = holder.imageLeague.getContext();
        Picasso.with(context).load(uri).into(holder.imageLeague);
//        holder.title.setText(league.getTitle());

    }

    @Override
    public int getItemCount() {
        return leagues.size();
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
