package com.example.osiris.testapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCompanyView(View view){
        Intent intent = new Intent(this, Company_OwnerIntent.class);
        startActivity(intent);
    }
    public void getEmployeeView(View view){
        Intent intent = new Intent(this, Employee_Intent.class);
        startActivity(intent);
    }
}
