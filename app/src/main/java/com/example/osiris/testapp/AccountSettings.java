package com.example.osiris.testapp;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountSettings extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText changeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        changeEmail = (EditText) findViewById(R.id.changeEmail);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
    }

    public void changeName(View view) {     //TO BE COMPLETED
    }

    public void changeEmail(View view) {    // NOT WORKING
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
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
    }
}
