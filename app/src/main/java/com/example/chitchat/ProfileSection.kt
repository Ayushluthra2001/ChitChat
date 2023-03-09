package com.example.chitchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class ProfileSection : AppCompatActivity() {


    private lateinit var name: TextView
    private lateinit var email: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_section)

        supportActionBar?.title="User Profile"

        name = findViewById(R.id.txtProfileName)

        val n = intent.getStringExtra("name");
        name.text=n

    }
}