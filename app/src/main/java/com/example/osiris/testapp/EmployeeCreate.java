package com.example.osiris.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeCreate extends AppCompatActivity {
  //  private Button button = (Button) findViewById(R.id.buttonCompanyCreate);
    private FirebaseAuth mAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_create);
        myRef = database.getReference().child("Users").child(mAuth.getCurrentUser().getUid());


        button = (Button)findViewById(R.id.buttonCompanyCreate);



    }

    public void saveCompany(View view) {
        myRef.push();
        String id = "COMPANY ID MAKE JUST ONE PLEASE";

        EditText et = (EditText) findViewById(R.id.editFieldCompanyCreate);
        User user = (User) getIntent().getSerializableExtra("theOwner");
        Company company = new Company(et.getText().toString(), user);
        myRef.child(id).setValue(company);
       // Log.d("Company check", company.getName() + " NAME" + company.getOwner().getEmail() + " OWNER");
        Intent intent = new Intent(this, CompanyO_Account.class);
        //intent.putExtra("MyCompany", company);




        finish();
        startActivity(intent);
//    }


    }
}
