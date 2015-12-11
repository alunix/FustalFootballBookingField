package com.hammersmith.fustalfootballbookingfield.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.model.FieldDetail;

import java.util.List;

/**
 * Created by USER on 11/17/2015.
 */
public class RecyclerAdapterSmallField extends RecyclerView.Adapter<RecyclerAdapterSmallField.MyViewHolder> {
    private static final String TAG = "CustomAdapter";
    private ClickListener clickListener;
    Context context;
    Activity activity;
    private List<FieldDetail> fieldDetails;
    FieldDetail fieldDetail;

    public RecyclerAdapterSmallField(FragmentActivity activity) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameField, priceMonFriMorn, priceMonFriEven, priceSatSunMorn, priceSatSunEven;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameField = (TextView) itemView.findViewById(R.id.numField);
            priceMonFriMorn = (TextView) itemView.findViewById(R.id.priceMonFriMorn);
            priceMonFriEven = (TextView) itemView.findViewById(R.id.priceMonFriEven);
            priceSatSunMorn = (TextView) itemView.findViewById(R.id.priceSatSunMorn);
            priceSatSunEven = (TextView) itemView.findViewById(R.id.priceSatSunEven);
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

    public RecyclerAdapterSmallField(Activity activity, List<FieldDetail> fieldDetails) {
        this.activity = activity;
        this.fieldDetails = fieldDetails;
    }

    @Override
    public RecyclerAdapterSmallField.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_field_detail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        fieldDetail = fieldDetails.get(position);
        holder.nameField.setText(fieldDetail.getName());
        holder.priceMonFriMorn.setText(fieldDetail.getWeekMrgPri());
        holder.priceMonFriEven.setText(fieldDetail.getWeekEvePri());
        holder.priceSatSunMorn.setText(fieldDetail.getWeekendMrgPri());
        holder.priceSatSunEven.setText(fieldDetail.getGetWeekendEvePri());
    }

    @Override
    public int getItemCount() {
        return fieldDetails.size();
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
}
