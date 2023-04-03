package com.example.chitchat;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class Registerj extends AppCompatActivity {


    public static final String TAG = "TAG";
        EditText mFullName,mEmail,mPassword,mPhone,mrefercode;
    Button mRegister,mloginswifter;
    TextView mLoginbtn,mtermc,mpprivacypolicy;
    ProgressBar progressBar;
    CheckBox mcheckbox;
    public  static  long points;
    long pointss  = 1000;
    String user;

    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        mFullName = findViewById(R.id.edt_name);
        mEmail = findViewById(R.id.edt_email);
        mPassword = findViewById(R.id.edt_password);
        mloginswifter = findViewById(R.id.btn_Login);

        mRegister = findViewById(R.id.btn_signup);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        mloginswifter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Loginj.class));
            }

        });

        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

                    mRegister.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {



                            final String Email = mEmail.getText().toString().trim();
                            String Password = mPassword.getText().toString().trim();
                            final String FullName = mFullName.getText().toString();



                            if (TextUtils.isEmpty(Email)) {
                                mEmail.setError("Email is Required");
                                return;
                            }
                            if (TextUtils.isEmpty(Password)) {
                                mPassword.setError("Password is Required");
                                return;
                            }

                            if (Password.length() < 6) {
                                mPassword.setError("Password Must Be Less Then 6 Character");
                            }










                            fAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {




                                        FirebaseUser fuser = fAuth.getCurrentUser();
                                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(getApplicationContext(), "Register Succesful", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "Onfailure: Email Not Sent" + e.getMessage());
                                            }
                                        });


                                        Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                                        userID = fAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fstore.collection("user").document(userID);
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("fname", FullName);
                                        user.put("Email", Email);
                                        user.put("PASSWORD", Password);
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Registerj.this, "Register Done", Toast.LENGTH_SHORT).show();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, "onfailure : " + e.toString());
                                            }
                                        });

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));


                                    } else {
                                        Toast.makeText(com.example.chitchat.Registerj.this, "Error Please Try Again", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(GONE);
                                        mtermc.setTextColor(Integer.parseInt("#FF0000"));
                                    }
                                }
                            });


                        }
                    });



        }














    }


