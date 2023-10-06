package com.example.litendapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.litendapp.databinding.ActivityMainBinding
import com.example.litendapp.ui.login.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.btnMenu.setOnClickListener{
//            Toast.makeText(this@MainActivity,"clicked in main",Toast.LENGTH_LONG).show();
//        }
        setContentView(binding.root)

        var auth = Firebase.auth;
        if(auth.currentUser==null){
            var intent = Intent(this@MainActivity,LoginActivity::class.java);
            startActivity(intent);
        }
        else{

            Toast.makeText(this@MainActivity,"Logged in User",Toast.LENGTH_LONG).show()
        }

    }
}