package com.example.osiris.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
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

public class CompanyO_Account extends AppCompatActivity {
    private User thisUser;
    private Button button;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private String companyKey;

    private ArrayList<String> list;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private User currentUser;
    private TextView hasNoComp;
    //private TextView tv3;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer__account);

        listView = (ListView)findViewById(R.id.ListViewCompany);
        list = new ArrayList<String>();



        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(arrayAdapter);

        button = (Button)findViewById(R.id.buttonGetUser);
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser().getUid();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                companyKey = currentUser.getCompanyKey();
                String email = (String) parent.getItemAtPosition(position);
                String currentUserKey = mAuth.getCurrentUser().getUid();

                Intent intent = new Intent(CompanyO_Account.this, checkEmployee.class);
                intent.putExtra("companyKey", companyKey);
                intent.putExtra("EMAIL", email);
                intent.putExtra("KEY", currentUserKey);
                startActivity(intent);

            }
        });
        final String email = getIntent().getStringExtra("EMAIL");

        //   final ListView listView = (ListView) findViewById(R.id.listCont);
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
                        checkForEmployees();
                        checkIfHasCompany();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hasNoComp = (TextView) findViewById(R.id.textViewEmailCheck);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
//        if (checkIfHasCompany() == false) {
//            hasNoComp.setText("You have no company! Create one");
//        } else {
//            hasNoComp.setText("You do have a company now yaay");
//        }
    }

    public void accSettings(View view) {
        Intent intent = new Intent(this, AccountSettings.class);
        startActivity(intent);
    }

    public void checkForEmployees(){
        arrayAdapter.clear();
        String compId = currentUser.getCompanyKey();
        if(compId == null){
            return;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child(compId).child("Employees");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if(user == null){
                    return;
                }else{
                    list.add(user.getEmail());
                    arrayAdapter.notifyDataSetChanged();
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


    }
    public void checkIfHasCompany() {

        String companyName = currentUser.getCompanyName();

        if (companyName == null) {
            hasNoComp.setText("You have no company");
        } else {
           hasNoComp.setText("You have the company: " + companyName);
        }
    }

    public User saveUser(User user) {
        thisUser = user;

        return thisUser;
    }


    public void goCreateCompany(View view) {
        Intent intent = new Intent(this, CompanyCreate.class);
        intent.putExtra("theOwner", currentUser);
        startActivity(intent);
    }


    public void getUser(View view) {

    }

    public void goCreateEmployee(View view) {
        Intent intent = new Intent(this, EmployeeCreate.class);
        intent.putExtra("companyKey", currentUser.getCompanyKey());
        startActivity(intent);
    }
}
