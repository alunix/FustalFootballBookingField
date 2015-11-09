package com.hammersmith.fustalfootballbookingfield.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.adapter.RoundImage;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.hammersmith.fustalfootballbookingfield.R;

/**
 * Created by minea2015 on 11/2/2015.
 */
public class getFacebookProfile extends AppCompatActivity {

    ProfilePictureView imgPro;
    TextView txtName,txtEmail,txtPhone;
    public String  fullname, email, gender,photo;
    Bitmap bitmap;
    //ImageView myOtherFBimg;
    RoundImage roundImage;
   // RoundImage roundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getactivityprofile);

        imgPro = (ProfilePictureView) findViewById(R.id.picture);

        txtName = (TextView) findViewById(R.id.txtNameFb);
        txtEmail = (TextView) findViewById(R.id.emailFb);

        Intent getintent = getIntent();
        fullname = getintent.getStringExtra(FacebookRegisterFragment.FULL_NAME);
        txtName.setText(fullname);
        email = getintent.getStringExtra(FacebookRegisterFragment.EMAIL);
        txtEmail.setText(email);
        photo = getintent.getStringExtra(FacebookRegisterFragment.PHOTO);
        imgPro.setProfileId(photo);

//        Toast.makeText(getApplicationContext(), "get !"+fullname, Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), "get !"+email, Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), "get !"+photo, Toast.LENGTH_LONG).show();
    }

    public Bitmap bitmapFromUrl(String src){

        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return  bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
