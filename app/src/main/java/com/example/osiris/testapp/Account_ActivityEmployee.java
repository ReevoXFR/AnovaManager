package com.example.osiris.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Account_ActivityEmployee extends AppCompatActivity {

    private Button button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__employee);

        mAuth = FirebaseAuth.getInstance();
        button = (Button)findViewById(R.id.logoutButtonEmployee);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                goB();     // just creating a new intent was giving an error?
            }
        });


    }//asdasd

    public void goB() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
