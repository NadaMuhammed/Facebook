package com.example.facebook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.facebook.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    lateinit var loginBtn: Button
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginBtn = binding.loginBtn
        loginBtn.setOnClickListener {
            val intent = Intent(this,PostActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}