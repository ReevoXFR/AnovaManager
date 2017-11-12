package com.example.osiris.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompanyO_Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final List<String> employeeList = new ArrayList<String>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer__account);
        final ListView listView = (ListView) findViewById(R.id.listCont);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        final TextView tv = (TextView) findViewById(R.id.textViewCont);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, employeeList);
        listView.setAdapter(adapter);
        myRef.child("Employees").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children){
                    Employee employee = child.getValue(Employee.class);
                    employeeList.add(employee.getName());
                    tv.append(employee.getName());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void accSettings(View view) {
        Intent intent = new Intent( this, AccountSettings.class);
        startActivity(intent);
    }
}
