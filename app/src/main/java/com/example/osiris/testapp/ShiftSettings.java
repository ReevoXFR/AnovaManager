package com.example.osiris.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ShiftSettings extends AppCompatActivity {

    private EditText day, month , year, startHour, startMin, endHour, endMin, day2,month2,year2;
    private Shift shift;
    private String key, id, ownerKey, employeeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_settings);

        shift = (Shift)getIntent().getSerializableExtra("SHIFT");
        key = getIntent().getStringExtra("KEY");

        id = getIntent().getStringExtra("ID");
        ownerKey = getIntent().getStringExtra("KEY2");
        employeeKey = getIntent().getStringExtra("EMPLOYEEKEY");

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

        day.setText(String.valueOf(shift.getSdd()));
        month.setText(String.valueOf(shift.getSmm()));
        year.setText(String.valueOf(shift.getSyy()));

        day2.setText(String.valueOf(shift.getEdd()));
        month2.setText(String.valueOf(shift.getEmm()));
        year2.setText(String.valueOf(shift.getEyy()));

        startHour.setText(String.valueOf(shift.getStartHour()));
        startMin.setText(String.valueOf(shift.getStartMin()));
        endHour.setText(String.valueOf(shift.getEndHour()));
        endMin.setText(String.valueOf(shift.getEndMin()));
    }

    public void modifyShift(View view) {


        int dd = Integer.parseInt(day.getText().toString());
        int mm = Integer.parseInt(month.getText().toString());
        int yy = Integer.parseInt(year.getText().toString());
        int dd2 = Integer.parseInt(day2.getText().toString());
        int mm2 = Integer.parseInt(month2.getText().toString());
        int yy2 = Integer.parseInt(year2.getText().toString());

        int startH = Integer.parseInt(startHour.getText().toString());
        int startM = Integer.parseInt(startMin.getText().toString());
        int endH = Integer.parseInt(endHour.getText().toString());
        int endM = Integer.parseInt(endMin.getText().toString());


        if(startHour.getText().toString().isEmpty() || startMin.getText().toString().isEmpty() || endHour.getText().toString().isEmpty() || endMin.getText().toString().isEmpty()) {
            Toast.makeText(ShiftSettings.this, "Please fill in time", Toast.LENGTH_LONG).show();
            return;
        }

        if(day.getText().toString().isEmpty() || day2.getText().toString().isEmpty() || month.getText().toString().isEmpty() || month2.getText().toString().isEmpty() || year.getText().toString().isEmpty() || year2.getText().toString().isEmpty()) {
            Toast.makeText(ShiftSettings.this, "Please fill in the date", Toast.LENGTH_LONG).show();
            return;
        }

        if(startH > 24 || startH<0 || endH > 24 || endH<0) {
            Toast.makeText(ShiftSettings.this, "Invalid hour", Toast.LENGTH_LONG).show();
            return;
        }
        if(startM > 60 || startM<0 || endM > 60 || endM<0) {
            Toast.makeText(ShiftSettings.this, "Invalid minute", Toast.LENGTH_LONG).show();
            return;
        }

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);


        if(dd > 32 || dd < 1 || dd2 > 32 || dd2 < 1 || mm > 12 || mm < 1 || mm2 > 12 || mm2 < 1 || yy < year-1 || yy>year+1 || yy2 < year-1 || yy2>year+1) {
            Toast.makeText(ShiftSettings.this, "Invalid date", Toast.LENGTH_LONG).show();
            return;
        }


        shift.setStartDateAndHour(yy, mm, dd, startH, startM);
        shift.setEndDateAndHour(yy2, mm2, dd2, endH, endM);
        shift.setUserKey(employeeKey);
//        currentUser.addShift(shift);         STILL NEED TO CHANGE THE SHIFTS IN "Shifts" AND "User"-Shifts
//        DatabaseReference myRef2 = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getKey());
//        myRef2.setValue(currentUser);
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Shifts");
//        final String id = myRef.push().getKey();
//        myRef.child(id).setValue(shift);



//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference().child("Users").child(key).child(id).child("Employees");       // TO BE IMPLEMENTED(Change shift in company-shifts)
//
//
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                User user = dataSnapshot.getValue(User.class);
//                if (user.getEmail().equals(getIntent().getStringExtra("EMAIL"))) {
//                    employeeKey = user.getKey();
//                    final DatabaseReference myRef2 = database.getReference().child("Users").child(ownerKey).child(id).child("Employees").child(employeeKey).child("Shifts");
//                    myRef2.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                            for (DataSnapshot child : children) {
//                                Shift shift = child.getValue(Shift.class);
//                                if(key.equals(child.getKey())) {
//                                    myRef2.child("Users").child(ownerKey).child(id).child("Employees").child(employeeKey).child("Shifts").child(key).setValue(shift);
//                                }
//                            }
//                        }
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//
//                    });
//                }
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }
}
