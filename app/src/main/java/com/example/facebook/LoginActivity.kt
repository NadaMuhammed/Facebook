package com.example.facebook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val intent = Intent(this,PostActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}