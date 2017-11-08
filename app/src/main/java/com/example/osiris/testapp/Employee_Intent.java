package com.example.osiris.testapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Employee_Intent extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__intent);

        mAuth = FirebaseAuth.getInstance();

        emailText = (EditText) findViewById(R.id.emailTextID);
        passwordText = (EditText) findViewById(R.id.passwordTextID);

        progressDialog = new ProgressDialog(this);

        loginButton = (Button)findViewById(R.id.buttonLoginID);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    if(mAuth.getCurrentUser().isEmailVerified()) {
                        startActivity(new Intent(Employee_Intent.this, Account_ActivityEmployee.class));
                    } else {
                        Toast.makeText(Employee_Intent.this, "Email not verified", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(Employee_Intent.this, "The fields are empty", Toast.LENGTH_LONG).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Employee_Intent.this, "Sign in problem", Toast.LENGTH_LONG).show();
                    }else{
                        finish();
                    }
                }
            });
            progressDialog.setMessage("Logging in...");
            progressDialog.show();

        }

    }


}
