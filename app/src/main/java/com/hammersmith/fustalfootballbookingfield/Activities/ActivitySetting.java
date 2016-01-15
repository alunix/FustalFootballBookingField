package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.hammersmith.fustalfootballbookingfield.widget.CircleTransform;
import com.squareup.picasso.Picasso;

public class ActivitySetting extends AppCompatActivity {
    User user;
    ImageView imageUser;
    EditText userName,email,phone,gander,dob;
    Context context = ActivitySetting.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        user = PrefUtils.getCurrentUser(ActivitySetting.this);
        imageUser = (ImageView) findViewById(R.id.imgUser);
        userName = (EditText) findViewById(R.id.edUsername);
        email = (EditText) findViewById(R.id.edEmail);
        phone = (EditText) findViewById(R.id.editPhone);
        gander = (EditText) findViewById(R.id.edgender);
        dob = (EditText) findViewById(R.id.eddob);

        if (phone.getText().toString().equals("")){
            phone.setText("Require");
            phone.setTextColor(getResources().getColor(R.color.red));
        }
        if (phone.isFocusable()){
            phone.setText("");
            phone.setTextColor(getResources().getColor(R.color.black));
        }

        Picasso.with(context).load("https://graph.facebook.com/" + user.facebookID + "/picture?type=large").into(imageUser);
        userName.setText(user.name);
        email.setText(user.email);

    }
}
