package com.example.chitchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    // initializing all the views
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText

    private lateinit var btnSignUp: Button

    private lateinit var mAuth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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

        // logic of creating the user .

    }
}