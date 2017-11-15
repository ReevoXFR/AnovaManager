package com.example.osiris.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class Employee_Account extends AppCompatActivity {

    private Button button;
    private FirebaseAuth mAuth;

    private Calendar calendar = Calendar.getInstance();

    private EditText day, month, year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__employee);

        day = (EditText) findViewById(R.id.day);
        month = (EditText) findViewById(R.id.month);
        year = (EditText) findViewById(R.id.year);
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
        int month2 = calendar.get(Calendar.MONTH);
        int year2 = calendar.get(Calendar.YEAR);

        day.setText(String.valueOf(day2));
        month.setText(String.valueOf(month2));
        year.setText(String.valueOf(year2));

        mAuth = FirebaseAuth.getInstance();
        button = (Button)findViewById(R.id.logoutButtonEmployee);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                goBackToMainPage();     // just creating a new intent was giving an error?
            }
        });


    }//asdasd

    public void goBackToMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void accSettings(View view) {
        Intent intent = new Intent( this, AccountSettings.class);
        startActivity(intent);
    }
}
