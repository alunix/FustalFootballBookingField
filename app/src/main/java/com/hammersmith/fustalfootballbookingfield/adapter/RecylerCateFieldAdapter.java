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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.CategoryField;
import com.hammersmith.fustalfootballbookingfield.model.Field;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private ClickListener clickListener;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        Context mContext;
        NetworkImageView imageField;

        public MyViewHolder(View itemView) {
            super(itemView);
            mContext = context;
            name = (TextView) itemView.findViewById(R.id.typeField);
            imageField = (NetworkImageView) itemView.findViewById(R.id.imageField);

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

    public RecylerCateFieldAdapter(Activity activity, List<CategoryField> categoryFields) {
        this.activity = activity;
        this.categoryFields = categoryFields;
    }

    @Override
    public RecylerCateFieldAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_type_field, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //------This one create for JsonArray-----/
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        if (imageLoader == null) {
//            imageLoader = AppController.getInstance().getImageLoader();

            categoryField = categoryFields.get(position);
            holder.name.setText(categoryField.getName());
            holder.imageField.setImageUrl(categoryField.getImage(), imageLoader);
//        }
    }

    @Override
    public int getItemCount() {
        return categoryFields.size();
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

}
