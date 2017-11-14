package com.example.osiris.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUser extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText emailTx, passwordTx, passwordTx2, nameTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        mAuth = FirebaseAuth.getInstance();

        emailTx = (EditText) findViewById(R.id.emailCreateUser);
        passwordTx = (EditText) findViewById(R.id.password);
        passwordTx2 = (EditText) findViewById(R.id.password2);
        nameTx = (EditText) findViewById(R.id.nameCreateUser);



    }

    public void createUser(View view) {
        String email = emailTx.getText().toString();
        final String password = passwordTx.getText().toString();
        String password2 = passwordTx2.getText().toString();

        if(!password.equals(password2)) {
            Toast.makeText(this, "Passwords do not match",
                    Toast.LENGTH_LONG).show();
            return;
        }


        //Method for creating a new user in the firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        mAuth.getCurrentUser().sendEmailVerification(); //send email verification
                        if (!task.isSuccessful()) return;
                        user employee = new user(nameTx.getText().toString(), emailTx.getText().toString());
                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Employees");
                        String id = myRef.push().getKey();
                        employee.setKey(id);
                        myRef.child(id).setValue(employee);//add
                        goMain();



                    }
                });
    }

    public void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
