package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.CategoryField;
import com.hammersmith.fustalfootballbookingfield.model.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/27/2015.
 */
public class RecylerCateFieldAdapter extends RecyclerView.Adapter<RecylerCateFieldAdapter.MyViewHolder> {
    private static final String TAG = "CustomAdapter";
    private Activity activity;
    Context context;
    private List<CategoryField> categoryFields;
    CategoryField categoryField;
    public ArrayList<String> typeField = new ArrayList<>();

    private ClickListener clickListener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.custom_type_field,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(root);
        return myViewHolder;
    }
    public RecylerCateFieldAdapter(){

    }
    public RecylerCateFieldAdapter(Context context,ContainerApplication ma){
        typeField.add("Small");
        typeField.add("Medium");
        typeField.add("Large");

        this.context = context;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(typeField.get(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.typeField);

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
        return typeField.size();
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
}
