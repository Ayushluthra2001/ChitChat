package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class spalsh : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
           finish()
        },1500)

    }
}