package com.example.osiris.testapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Employee_Login extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    //private Button loginButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressDialog progressDialog;

    CircularProgressButton circularProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__login);

       circularProgressButton = (CircularProgressButton) findViewById(R.id.buttonLoginID);

        mAuth = FirebaseAuth.getInstance();

        emailText = (EditText) findViewById(R.id.emailTextID);
        passwordText = (EditText) findViewById(R.id.passwordTextID);

        progressDialog = new ProgressDialog(this);

        //loginButton = (Button) findViewById(R.id.buttonLoginID);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {

                }
            }
        };

        /*
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });*/

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("mail", emailText.getText().toString());
        editor.putString("password", passwordText.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String mail = prefs.getString("mail", "");
        String password = prefs.getString("password", "");
        emailText.setText(mail);
        passwordText.setText(password);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {
        //progressDialog.setMessage("Logging in ...");
        //progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(Employee_Login.this, "The fields are empty", Toast.LENGTH_LONG).show();

        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (mAuth.getCurrentUser().isEmailVerified() == false) {
                        Toast.makeText(Employee_Login.this, "Email not verified", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!task.isSuccessful()) {
                        Toast.makeText(Employee_Login.this, "Sign in problem", Toast.LENGTH_LONG).show();
                        circularProgressButton.stopAnimation();
                        return;
                    } else {

                        circularProgressButton.doneLoadingAnimation(Color.parseColor("#333693"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                        startActivity(new Intent(Employee_Login.this, Employee_Account.class));
                        finish();
                        //progressDialog.dismiss();
                    }
                }
            });



        }

    }

    public void resetPassword(View view) {
        Intent intent = new Intent( this, Reset_Password.class);
        startActivity(intent);
    }

    public void loginEmployee(View view){
        circularProgressButton.startAnimation();
        startSignIn();
    }


}
