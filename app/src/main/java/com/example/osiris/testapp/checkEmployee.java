package com.example.osiris.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    private ArrayList<String> shifts, keys;
    private ArrayList<Shift> realShifts;
    private ArrayAdapter<String> arrayAdapter;
    private String employeeKey, key, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_employee);
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.shiftListView);

        final String key = getIntent().getStringExtra("KEY");
        final String id = getIntent().getStringExtra("companyKey");

        shifts = new ArrayList<>();
        keys = new ArrayList<>();
        realShifts = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shifts);
        listView.setAdapter(arrayAdapter);

        checkForEmployees();
    }

    public void checkForEmployees() {
        key = getIntent().getStringExtra("KEY");
        id = getIntent().getStringExtra("companyKey");
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
                                keys.add(child.getKey());
                                shifts.add(shift.toString());
                                realShifts.add(shift);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id){
               // Toast.makeText(checkEmployee.this, String.valueOf(position), Toast.LENGTH_LONG).show();

                Shift shift = realShifts.get(position);

                Intent intent = new Intent(checkEmployee.this, ShiftSettings.class);
                intent.putExtra("SHIFT", shift);
                intent.putExtra("KEY", keys.get(position));
                intent.putExtra("KEY2", key);
                intent.putExtra("ID", id);
                intent.putExtra("EMPLOYEEKEY", employeeKey);
                startActivity(intent);
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

    public void removeEmployee(View view) {

        Toast.makeText(checkEmployee.this, "Not implemented yet", Toast.LENGTH_LONG).show();

//        final String key = getIntent().getStringExtra("KEY");
//        final String id = getIntent().getStringExtra("companyKey");
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference().child("Users");
//        final String email = getIntent().getStringExtra("EMAIL");
//
//        myRef.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                for (DataSnapshot child : children) {
//                    User user = child.getValue(User.class);
//                    if (user.getEmail().equals(email)) {
//                        Log.d("AAAAAAAAAAA", user.getEmail());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//

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
