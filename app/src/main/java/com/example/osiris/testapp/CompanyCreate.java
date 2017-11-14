package com.example.osiris.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CompanyCreate extends AppCompatActivity {
  //  private Button button = (Button) findViewById(R.id.buttonCompanyCreate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_create);


    }

    public void saveCompany(View view) {
        EditText et = (EditText) findViewById(R.id.editFieldCompanyCreate);
        User user = (User) getIntent().getSerializableExtra("theOwner");
        Company company = new Company(et.getText().toString(), user);
        Log.d("Company check", company.getName() + " NAME" + company.getOwner().getEmail() + " OWNER");
//        Intent intent = new Intent(this, CompanyO_Account.class);
//        intent.putExtra("MyCompany", company);
//        finish();
//        startActivity(intent);
//    }


    }
}
