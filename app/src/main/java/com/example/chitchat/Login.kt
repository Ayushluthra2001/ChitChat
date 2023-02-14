package com.example.chitchat


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {


    // initializing all the views

    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnSignUp:Button
    private lateinit var btnLogin: Button
    private lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth= FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnLogin=findViewById(R.id.btn_login)
        btnSignUp=findViewById(R.id.btn_signup)


        // Intent
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    btnLogin.setOnClickListener {

        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()


        login(email, password)

    }



    }

    // functions
    private fun login(email : String , password : String ){
        // logic for logging user
    }
}