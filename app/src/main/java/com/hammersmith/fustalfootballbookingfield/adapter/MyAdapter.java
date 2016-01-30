package com.hammersmith.fustalfootballbookingfield.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.Activities.RegisterActivity;
import com.hammersmith.fustalfootballbookingfield.Container.ContainerApplication;
import com.hammersmith.fustalfootballbookingfield.Fragments.FragmentFb_Google;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.TabMain.ContainerFragment;
import com.hammersmith.fustalfootballbookingfield.widget.CircleTransform;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by minea2015 on 12/23/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    Context context;
    String name;        //String Resource for header View Name
    String profile;        //int Resource for header view profile picture
    String email;       //String Resource for header view email

    JSONObject response, profile_pic_data, profile_pic_url;


    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        Context context;
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;

        public ViewHolder(View itemView, int ViewType, Context context) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            } else {


                Name = (TextView) itemView.findViewById(R.id.txtPro);         // Creating Text View object from header.xml for name
                email = (TextView) itemView.findViewById(R.id.txtEmail);       // Creating Text View object from header.xml for email
                profile = (ImageView) itemView.findViewById(R.id.imgPro);// Creating Image view object from header.xml for profile pic
                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }


        @Override
        public void onClick(View v) {

            ContainerApplication mainActivity = (ContainerApplication) context;
            mainActivity.mDrawer.closeDrawers();
            FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();
            //ContainerFragment containFragment;
            switch (getPosition()) {
                case 1:
                    Fragment containFragment = new ContainerFragment();
                    fragmentTransaction.replace(R.id.continer_framelayout, containFragment).commit();
                    break;
                case 2:
                    Fragment contain = new ContainerFragment();
                    fragmentTransaction.replace(R.id.continer_framelayout, contain).commit();
                    break;
                case 3:
                    Fragment contains = new ContainerFragment();
                    fragmentTransaction.replace(R.id.continer_framelayout, contains).commit();
                    break;
                case 4:
                    Fragment contains_f = new ContainerFragment();
                    fragmentTransaction.replace(R.id.continer_framelayout, contains_f).commit();
                    break;
                case 5:
                    Fragment contain_ff = new ContainerFragment();
                    fragmentTransaction.replace(R.id.continer_framelayout, contain_ff).commit();
                    break;
                case 6:

                    FacebookSdk.sdkInitialize(context.getApplicationContext());
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(context, RegisterActivity.class);
                    context.startActivity(intent);


                    break;

            }
        }
    }


    public MyAdapter(String Titles[], int Icons[], String Name, String Email, String Profile, Context context) { // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        this.mNavTitles = Titles;                //have seen earlier
        this.mIcons = Icons;

        this.name = Name;
        this.email = Email;
        this.profile = Profile;                     //here we assign those passed values to the values we declared here
        this.context = context;
        //in adapter


    }


    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false); //Inflating the layout
            ViewHolder vhItem = new ViewHolder(v, viewType, context); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false); //Inflating the layout
            ViewHolder vhHeader = new ViewHolder(v, viewType, context); //Creating ViewHolder and passing the object of type view

            return vhHeader; //returning the object created


        }
        return null;

    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position - 1]);// Settimg the image with array of our icons
        } else {
            // Picasso.with(context).load("https://graph.facebook.com/" + position + "/picture?type=large").transform(new CircleTransform()).into(holder.profile);
            holder.profile.setImageResource(Integer.parseInt(profile));
            holder.Name.setText(name);
            holder.email.setText(email);

        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mNavTitles.length + 1; // the number of items in the list will be +1 the titles including the header view.
    }


    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}