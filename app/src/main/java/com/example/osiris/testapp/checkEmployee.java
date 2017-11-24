package com.example.osiris.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Osiris on 11/21/2017.
 */

public class checkEmployee extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> shifts;
    private ArrayAdapter<String> arrayAdapter;
    private String employeeKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_employee);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.shiftListView);

        shifts = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shifts);
        listView.setAdapter(arrayAdapter);

        checkForEmployees();


    }

    public void checkForEmployees() {
        final String key = getIntent().getStringExtra("KEY");
        final String id = getIntent().getStringExtra("companyKey");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Users").child(key).child(id).child("Employees");


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if (user.getEmail().equals(getIntent().getStringExtra("EMAIL"))) {
                    employeeKey = user.getKey();
                    DatabaseReference myRef2 = database.getReference().child("Users").child(key).child(id).child("Employees").child(employeeKey).child("Shifts");
                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                            for (DataSnapshot child : children) {
                                Shift shift = child.getValue(Shift.class);
                                shifts.add(shift.toString());
                                arrayAdapter.notifyDataSetChanged();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                Log.d("BBBB", "BBBBB");
//                for (DataSnapshot child : children) {
////                    Shift shift = child.getValue(Shift.class);
////                    shifts.add(shift.toString());
////                    Log.d("CCC", "CCC");
////                    arrayAdapter.notifyDataSetChanged();
//                    User user = dataSnapshot.getValue(User.class);
//                    if (user.getEmail().equals(getIntent().getStringExtra("EMAIL"))) {
//                        employeeKey = user.getKey();
//                    }
//
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
    }


//    public void checkForEmployees2() {
//        String key = getIntent().getStringExtra("KEY");
//        String id = getIntent().getStringExtra("companyKey");
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef2 = database.getReference().child("Users").child(key).child(id).child("Employees").child(employeeKey).child("Shifts");
//        myRef2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                Log.d("BBBB", "BBBBB");
//                for (DataSnapshot child : children) {
//                    Shift shift = child.getValue(Shift.class);
//                   shifts.add(shift.toString());
//                    Log.d("CCC", "CCC");
//                    arrayAdapter.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//    }
}
