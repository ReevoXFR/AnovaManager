package com.example.osiris.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanyCreate extends AppCompatActivity {
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

        String id = myRef.push().getKey();

        EditText et = (EditText) findViewById(R.id.editFieldCompanyCreate);
        User user = (User) getIntent().getSerializableExtra("theOwner");
        user.setCompanyKey(id);
        user.setCompanyName(et.getText().toString());
        myRef.setValue(user);
        Company company = new Company(et.getText().toString(), user.getKey());
        myRef.child(id).setValue(company);

        Intent intent = new Intent(this, CompanyO_Account.class);
        intent.putExtra("companyKey", id);
        intent.putExtra("companyName", et.getText().toString());

        finish();
        startActivity(intent);
//    }


    }
}
