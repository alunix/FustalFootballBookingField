package com.hammersmith.fustalfootballbookingfield.Activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hammersmith.fustalfootballbookingfield.DateDialog;
import com.hammersmith.fustalfootballbookingfield.R;
import com.hammersmith.fustalfootballbookingfield.controller.AppController;
import com.hammersmith.fustalfootballbookingfield.model.User;
import com.hammersmith.fustalfootballbookingfield.model.UserUpdate;
import com.hammersmith.fustalfootballbookingfield.utils.Constant;
import com.hammersmith.fustalfootballbookingfield.utils.PrefUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditUsers extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener{
    User user;
    Context context;
    TextView next, cancel;
    EditText name,email,phone,address,gender,dob;
    UserUpdate userUpdate;
    String strgender;
    ImageView imageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);

        user = new User();
        userUpdate = new UserUpdate();
        user = PrefUtils.getCurrentUser(EditUsers.this);

        imageUser = (ImageView) findViewById(R.id.imageUser);
        next = (TextView) findViewById(R.id.next);
        cancel = (TextView) findViewById(R.id.cancel);
        name = (EditText) findViewById(R.id.edname);
        email = (EditText) findViewById(R.id.edemail);
        phone = (EditText) findViewById(R.id.edphonenumber);
        address = (EditText) findViewById(R.id.edaddress);
        gender = (EditText) findViewById(R.id.edgender);
        dob = (EditText) findViewById(R.id.eddob);

        name.setText(user.getName());
        email.setText(user.getEmail());
        Picasso.with(context).load("https://graph.facebook.com/" + user.getFacebookID() + "/picture?type=large").into(imageUser);

        cancel.setOnClickListener(this);
        next.setOnClickListener(this);
        name.setOnFocusChangeListener(this);
        gender.setOnFocusChangeListener(this);
        phone.setOnFocusChangeListener(this);
        address.setOnFocusChangeListener(this);
        dob.setOnFocusChangeListener(this);

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.edname:
                if (hasFocus) {
                    String strname = name.getText().toString();
                    userUpdate.setName(strname);
                }
                break;
            case R.id.edphonenumber:
                if (hasFocus) {
                    String strphone = phone.getText().toString();
                    userUpdate.setPhone(strphone);
                }
                break;
            case R.id.edaddress:
                if (hasFocus) {
                    String straddress = address.getText().toString();
                    userUpdate.setAddress(straddress);
                }
                break;
            case R.id.edgender:
                if (hasFocus) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    final CharSequence[] array = {"Male", "Female"};
                    builder.setTitle("Select Gender")
                            .setSingleChoiceItems(array, 2, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    strgender = (String) array[which];
                                }
                            })

                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    gender.setText(strgender);
                                    userUpdate.setGender(strgender);
                                    gender.clearFocus();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    builder.show();
                }
                break;
            case R.id.eddob:
                if (hasFocus) {
                    DateDialog dateDialog = new DateDialog(view);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dateDialog.show(ft, "DatePicker");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(dob.getWindowToken(), 0);
                    String strdob = dob.getText().toString();
                    userUpdate.setDob(strdob);
                    dob.clearFocus();
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                break;
            case R.id.next:
                updateUser();
                break;
        }
    }
    public void updateUser() {
        StringRequest userReq = new StringRequest(Request.Method.POST, Constant.URL_UPDATE + user.getFacebookID() + "/update", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Toast.makeText(getApplicationContext(), "Data uploaded...", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("phone", gender.getText().toString());
                params.put("gender", phone.getText().toString());
                params.put("address", address.getText().toString());
                params.put("dob", dob.getText().toString());

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(userReq);
    }
}
