package com.example.osiris.testapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        GregorianCalendar gc = new GregorianCalendar();
        Log.d("asd", gc.getTime().toString());


    }

    public void getCompanyView(View view){
        Intent intent = new Intent(this, CompanyO_Login.class);
        startActivity(intent);
    }
    public void getEmployeeView(View view){
        Intent intent = new Intent(this, Employee_Login.class);
        startActivity(intent);
    }

    public void createNewUser(View view) {
        Intent intent = new Intent(this, CreateUser.class);
        startActivity(intent);
    }





}
