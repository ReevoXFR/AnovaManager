package com.example.osiris.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CompanyO_Account extends AppCompatActivity {
    private User thisUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User currentUser;
    private TextView hasNoComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final List<String> employeeList = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer__account);
        //   final ListView listView = (ListView) findViewById(R.id.listCont);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //GETTING THE USER HERE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        myRef.child("Employees").addValueEventListener(new ValueEventListener() {


            final String email = getIntent().getStringExtra("EMAIL");
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {


                    User user = child.getValue(User.class);

                    if (user.getEmail().equals(email)) {
                        saveUser(user);
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        thisUser.getEmail();
        //Log.d("FINALLY", thisUser.getEmail());


        final TextView tv = (TextView) findViewById(R.id.textViewCont);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, employeeList);
        //  listView.setAdapter(adapter);

        hasNoComp = (TextView) findViewById(R.id.textViewEmailCheck);
        //String emailtest = thisUser.getEmail();
        //Log.d("companies",emailtest);
        if (checkIfHasCompany() == false) {
            hasNoComp.setText("You have no company! Create one");
        } else {
            hasNoComp.setText("");
        }

        // THIS METHOD HERE will help with adding employees using the key
//        myRef.child("Employees").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                for (DataSnapshot child : children) {
//                    String key = "-KyuI-bR1HZwfLA8NT_P";
//
//                    if (child.getKey().equals(key)) {
//                        User user = child.getValue(User.class);
//                        employeeList.add(user.getEmail());
//                        tv.append(user.getName() + " \n");
//                        tv.append(user.getEmail() + "\n");
//                        tv.append(user.getKey() + "\n");
//
//                        adapter.notifyDataSetChanged();
//                    }
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (checkIfHasCompany() == false) {
            hasNoComp.setText("You have no company! Create one");
        } else {
            hasNoComp.setText("You do have a company now yaay");
        }
    }

    public void accSettings(View view) {
        Intent intent = new Intent(this, AccountSettings.class);
        startActivity(intent);
    }

    public boolean checkIfHasCompany() {
        //not available from up there idk why
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String email = getIntent().getStringExtra("EMAIL");
        final boolean[] a = {false};
        DatabaseReference myRef = database.getReference();
        Toast.makeText(this, "lala", Toast.LENGTH_SHORT).show();
        myRef.child("Employees").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {

                    User user = child.getValue(User.class);
                    Log.d("new try ", user.getEmail() + " here");
                    if (user.getEmail().equals(email) && user.getCompanies() != null) {
                        a[0] = true;
                    } else {
                        Log.d("", "has no company");
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return a[0];

    }

    public User saveUser(User user) {
        thisUser = user;

        return thisUser;
    }


    public void goCreate(View view) {
        Intent intent = new Intent(this, CompanyCreate.class);
        intent.putExtra("theOwner",currentUser);
        startActivity(intent);
    }


}
