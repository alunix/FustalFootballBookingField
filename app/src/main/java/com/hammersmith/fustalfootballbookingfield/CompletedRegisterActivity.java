package com.hammersmith.fustalfootballbookingfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class CompletedRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        setContentView(R.layout.activity_completed_register);

    }

    public void cancel(View view) {
        Intent intent = new Intent().setClass(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent().setClass(this,ContainerApplication.class);
        startActivity(intent);
    }
}
