package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    // initializing all the views
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText

    private lateinit var btnSignUp: Button

    private lateinit var mAuth : FirebaseAuth

    private lateinit var mDbRef : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edtName=findViewById(R.id.edt_name)
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnSignUp=findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val name=edtName.text.toString()
            val email=edtEmail.text.toString()
            val password=edtPassword.text.toString()


            signUp(name,email,password)
        }
    }

    private fun signUp(name: String , email: String, password : String){

        // logic of creating the new user .
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code for jumping to home activity

                    // getting current user unique id
                    val uid=mAuth.currentUser?.uid
                    // adding user to database
                    addUserToDatabase(email , name , uid!!) // !! use for null safe

                    val intent = Intent(this,MainActivity::class.java)

                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this,"Email id is already registered ",Toast.LENGTH_SHORT).show()
                }
            }
    }


    // method of adding user to the database
    private fun addUserToDatabase(email : String , name : String , uid : String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        // child will add the node in data base .

        mDbRef.child("user").child(uid).setValue(User(name ,email,uid))
    }
}