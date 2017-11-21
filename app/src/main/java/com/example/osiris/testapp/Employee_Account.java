package com.example.osiris.testapp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Employee_Account extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    private EditText day, month , year, startHour, startMin, endHour, endMin, day2,month2,year2;
    private Calendar calendar;
    private User currentUser;
    private String email, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__employee);

        email = getIntent().getStringExtra("EMAIL");

        day = (EditText) findViewById(R.id.dd);
        month = (EditText) findViewById(R.id.mm);
        year = (EditText) findViewById(R.id.yy);

        day2 = (EditText) findViewById(R.id.edd);
        month2 = (EditText) findViewById(R.id.emm);
        year2 = (EditText) findViewById(R.id.eyy);

        startHour = (EditText) findViewById(R.id.startHour);
        startMin = (EditText) findViewById(R.id.startMin);
        endHour = (EditText) findViewById(R.id.endHour);
        endMin = (EditText) findViewById(R.id.endMins);

        calendar = Calendar.getInstance();

        day.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        month.setText(String.valueOf(calendar.get(Calendar.MONTH)));
        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));

        day2.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        month2.setText(String.valueOf(calendar.get(Calendar.MONTH)));
        year2.setText(String.valueOf(calendar.get(Calendar.YEAR)));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawable_menu_employee_account);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //GETTING THE USER HERE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    User user = child.getValue(User.class);
                    if (user.getEmail().equals(email)) {
                        currentUser = user;
                        key = child.getKey();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goBackToMainPage(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void accSettings(MenuItem item) {
        Intent intent = new Intent( this, AccountSettings.class);
        startActivity(intent);
    }

    public void addShift(View view) {
        int dd = Integer.parseInt(day.getText().toString());
        int mm = Integer.parseInt(month.getText().toString());
        int yy = Integer.parseInt(year.getText().toString());
        int dd2 = Integer.parseInt(day2.getText().toString());
        int mm2 = Integer.parseInt(month2.getText().toString());
        int yy2 = Integer.parseInt(year2.getText().toString());
        //Shift shift = new Shift(dd,mm,yy, Integer.parseInt(startHour.getText().toString()), Integer.parseInt(startMin.getText().toString()));
        Shift shift = new Shift();
        shift.setStartDateAndHour(dd,mm,yy, Integer.parseInt(startHour.getText().toString()), Integer.parseInt(startMin.getText().toString()));
        int endH = Integer.parseInt(endHour.getText().toString());
        int endM = Integer.parseInt(endMin.getText().toString());
        shift.setEndDateAndHour(dd,mm,yy, endH, endM );

        currentUser.addShift(shift);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference();


        User user = currentUser;

       // myRef.child("Shifts").setValue(shift);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Shifts");
        myRef.push();
        myRef.child("Shifts").setValue(shift);





    }
}






