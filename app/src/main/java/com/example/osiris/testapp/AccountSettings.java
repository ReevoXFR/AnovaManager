package com.example.osiris.testapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountSettings extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText changeEmail, pass, pass2, oldpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        mAuth = FirebaseAuth.getInstance();
        changeEmail = (EditText) findViewById(R.id.changeEmail);
        pass = (EditText) findViewById(R.id.passwordAccSettings);
        pass2 = (EditText) findViewById(R.id.passwordAccSettings2);
        oldpass = (EditText) findViewById(R.id.oldPasswordAccSettings);
    }

    public void changeName(View view) {     //TO BE COMPLETED
    }

    public void changeEmail(View view) {
        FirebaseUser user = mAuth.getCurrentUser();

        String email = changeEmail.getText().toString();

        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AccountSettings.this, "Email updated", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    public void changePassword(View view) {
        String password = pass.getText().toString();
        String password2 = pass.getText().toString();
        String oldPass = oldpass.getText().toString();

        FirebaseUser user = mAuth.getCurrentUser();


        if(!password.equals(password2)) {
            Toast.makeText(this, "Passwords do not match",
                    Toast.LENGTH_LONG).show();
            return;
        }

        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), oldPass);
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {     // to check if old password is correct
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(AccountSettings.this, "Old password is incorrect", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



        user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AccountSettings.this, "Password updated", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
