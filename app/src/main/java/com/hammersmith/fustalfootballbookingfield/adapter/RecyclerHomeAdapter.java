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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by USER on 11/2/2015.
 */
public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.MyViewHolder> {
    private static final String TAG = "CustomAdapter";
    private Activity activity;
    private List<Field> fields;
    Field field;
    Context context;

    private ClickListener clickListener;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        ImageView imgField;
        TextView name, location;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            imgField = (ImageView) itemView.findViewById(R.id.cover);
            name = (TextView) itemView.findViewById(R.id.nameField);
            location = (TextView) itemView.findViewById(R.id.locationField);
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

    public RecyclerHomeAdapter(Activity activity, List<Field> fields) {
        this.activity = activity;
        this.fields = fields;
    }

    @Override
    public RecyclerHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cardview_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerHomeAdapter.MyViewHolder holder, int position) {
        field = fields.get(position);
        Uri uri = Uri.parse(field.getImage());
        context = holder.imgField.getContext();
        Picasso.with(context).load(uri).into(holder.imgField);
        holder.name.setText(field.getName());
        holder.location.setText(field.getLocation());
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
