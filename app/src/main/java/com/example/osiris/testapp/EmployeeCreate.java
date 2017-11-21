package com.example.osiris.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeCreate extends AppCompatActivity {
  //  private Button button = (Button) findViewById(R.id.buttonCompanyCreate);
    private FirebaseAuth mAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef1;
    private DatabaseReference myRef2;
    private Button button;
    private User employeeToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_create);
        mAuth = FirebaseAuth.getInstance();







    }

    public void addEmployee(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();




        final EditText et = (EditText) findViewById(R.id.editFieldAddEmployee);
        myRef = database.getReference().child("Users");
        myRef2 = database.getReference().child("Users").child(mAuth.getCurrentUser().getUid()).child("COMPANY ID MAKE JUST ONE PLEASE").child("Employees");
        final String key = String.valueOf(et.getText());


        myRef.child("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                DataSnapshot ds;

                for (DataSnapshot child : children) {

                    //User user = new User(dataSnapshot.child("name").getValue(String.class), dataSnapshot.child("email").getValue(String.class));
                    User user = child.getValue(User.class);

                    if (user.getEmail().equals(String.valueOf(et.getText()))) {

                        myRef2.push();
                        String id = user.getKey();
                        myRef2.child(id).setValue(user);

                    }else{
                        Log.d("No such User", "No such user!");
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        // Log.d("Company check", company.getName() + " NAME" + company.getOwner().getEmail() + " OWNER");
//        Intent intent = new Intent(this, CompanyO_Account.class);
//        //intent.putExtra("MyCompany", company);
//        finish();
//        startActivity(intent);

    }
    }




