package com.example.chitchat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.firestore.FirebaseFirestore

class spalsh : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
//        val sharedPref=this?.getPreferences(Context.MODE_PRIVATE)?:return
//        val isLogin=sharedPref.getString("email","1")



        supportActionBar?.hide()
        Handler().postDelayed({
//            if(isLogin=="1"){
//                val email = intent.getStringExtra("email")
//                if(email!=null){
//                    with(sharedPref.edit()){
//                        putString("email",email)
//                        apply()
//
//
//                    }
//                    val intent=Intent(this,Login::class.java)
//                    startActivity(intent)
//                    finish()
//                }else{
//                 val intent=Intent(this,SignUp::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//            }else{
//                setLogin(isLogin)
//                val intent = Intent(this,MainActivity::class.java);
//                startActivity(intent)
//                finish()
//            }

            val intent=Intent(this,Registerj::class.java)
            startActivity(intent)
            finish()
        },1500)

    }


}