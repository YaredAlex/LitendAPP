package com.example.litendapp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.litendapp.R
import com.example.litendapp.databinding.ActivityRegisterBinding
import com.example.litendapp.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var binding :ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var toLogin = binding.toLogin
        var contentBox = binding.logBox
        var anim2 = AnimationUtils.loadAnimation(this,R.anim.from_bottom_to_top)
        var btnRegister = binding.login
        contentBox.startAnimation(anim2)
        toLogin.setOnClickListener {
            var intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnRegister.setOnClickListener {
            Toast.makeText(this@RegisterActivity,"Not Implemented! Please Wait!",Toast.LENGTH_LONG).show();
        }
    }
}