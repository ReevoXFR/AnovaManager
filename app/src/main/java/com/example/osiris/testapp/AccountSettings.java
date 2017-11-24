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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountSettings extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText changeEmail, pass, pass2, oldpass, changeName;
    private User currentUser;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        changeEmail = (EditText) findViewById(R.id.changeEmail);
        pass = (EditText) findViewById(R.id.passwordAccSettings);
        pass2 = (EditText) findViewById(R.id.passwordAccSettings2);
        oldpass = (EditText) findViewById(R.id.oldPasswordAccSettings);
        changeName = (EditText) findViewById(R.id.changeName);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        final String email = getIntent().getStringExtra("EMAIL");
        //GETTING THE USER HERE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        myRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    //User user = new User(dataSnapshot.child("name").getValue(String.class), dataSnapshot.child("email").getValue(String.class));
                    User user = child.getValue(User.class);
                    if (user.getEmail().equals(email)) {
                        currentUser = user;
                        id = child.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changeName(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        User user = currentUser;
        user.setName(changeName.getText().toString());
        myRef.child("Users").child(id).setValue(user);
    }

    public void changeEmail(View view) {
        final FirebaseUser user = mAuth.getCurrentUser();

        String email = changeEmail.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final User user2 = currentUser;
        user2.setEmail(email);

        user.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AccountSettings.this, "Email updated", Toast.LENGTH_LONG).show();
                                        Toast.makeText(AccountSettings.this, "Please verify the new mail", Toast.LENGTH_LONG).show();
                                        myRef.child("Users").child(id).setValue(user2);

                                    } else {
                                        Toast.makeText(AccountSettings.this, "Unexpected error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(AccountSettings.this, "Invalid email", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    public void changePassword(View view) {
        final String password = pass.getText().toString();
        String password2 = pass2.getText().toString();
        String oldPass = oldpass.getText().toString();

        final FirebaseUser user = mAuth.getCurrentUser();


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
                    user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AccountSettings.this, "Password updated", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    Toast.makeText(AccountSettings.this, "Password changed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AccountSettings.this, "Old password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
