package com.example.osiris.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Osiris on 11/21/2017.
 */

public class SeeShifts2 extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> list;
    private ArrayAdapter<String>arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_shifts);

        String ownerKey = getIntent().getStringExtra("ownerKey");
        String userKey = getIntent().getStringExtra("key");

        list = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listOfShifts);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);


        listView.setAdapter(arrayAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Log.d("Check this", ownerKey + " " + userKey);

        myRef.child("Users").child(ownerKey).child("COMPANY ID MAKE JUST ONE PLEASE").child("Employees").child(userKey).child("Shifts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                DataSnapshot ds;

                for (DataSnapshot child : children) {

                    //User user = new User(dataSnapshot.child("name").getValue(String.class), dataSnapshot.child("email").getValue(String.class));
                    Shift shift = child.getValue(Shift.class);
                    list.add(shift.toString());
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
