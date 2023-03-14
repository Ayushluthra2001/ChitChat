package com.example.chitchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CurrentUserActivity : AppCompatActivity() {
    private lateinit var name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_user)
        supportActionBar?.title="Your Profile"

        name = findViewById<TextView>(R.id.currentProfileName)

        val n = intent.getStringExtra("name");
        name.text=n
    }
}