package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginj extends AppCompatActivity {


    EditText mEmail,mPassword;
    Button mlogindbtn;
    Button mRegn;
    ProgressBar progressBar;

    FirebaseAuth fAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mEmail = findViewById(R.id.edt_email);
        mPassword = findViewById(R.id.edt_password);
        mRegn = findViewById(R.id.btn_signup);
        mlogindbtn = findViewById(R.id.btn_login);
        fAuth = FirebaseAuth.getInstance();





        mlogindbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();


                if (TextUtils.isEmpty(Email))
                {
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(Password))
                {
                    mPassword.setError("Password is Required");
                }

                if (Password.length() < 6)
                {
                    mPassword.setError("Password must be > = 6 character");
                    return;
                }




                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        }

                        else
                        {

                            Toast.makeText(getApplicationContext(), "Please Enter Valid Email & Password", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }


                });



            }

        });

        mRegn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registerj.class));
                finish();
            }

        });




    }
}