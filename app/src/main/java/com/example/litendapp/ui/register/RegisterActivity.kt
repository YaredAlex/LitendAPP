package com.example.litendapp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.litendapp.MainActivity
import com.example.litendapp.R
import com.example.litendapp.data.model.RegisterUserModel
import com.example.litendapp.databinding.ActivityRegisterBinding
import com.example.litendapp.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var binding :ActivityRegisterBinding
    lateinit var registerViewModel :RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var toLogin = binding.toLogin
        var contentBox = binding.logBox
        registerViewModel = RegisterViewModel(this@RegisterActivity);
        var anim2 = AnimationUtils.loadAnimation(this,R.anim.from_bottom_to_top)
        var btnRegister = binding.login
        var registerUserModel = RegisterUserModel()
        contentBox.startAnimation(anim2)
        toLogin.setOnClickListener {
            var intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnRegister.setOnClickListener {
            //Toast.makeText(this@RegisterActivity,"Not Implemented! Please Wait!",Toast.LENGTH_LONG).show();
            registerUserModel.email  = binding.email.text.toString();
            registerUserModel.userName = binding.username.text.toString();
            registerUserModel.phoneNumber = binding.phone.text.toString();
            registerUserModel.password = binding.password.text.toString();
            registerViewModel.register(registerUserModel)
        }
        registerViewModel.finished.observe(this@RegisterActivity){
            if (it==1)
                binding.registerProgress.visibility = View.GONE
            else if(it==2)
                startMain()
            else if(it==0)
                binding.registerProgress.visibility = View.VISIBLE

        }
    }
    fun startMain(){
        var intent = Intent(this@RegisterActivity,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}