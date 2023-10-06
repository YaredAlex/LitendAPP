package com.example.litendapp.ui.login

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.litendapp.databinding.ActivityLoginBinding

import com.example.litendapp.R
import com.example.litendapp.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)
        val username = binding.username
        val password = binding.password
        val login  = binding.login
        val loading = binding.loading
        val toSignin = binding.txtHaveAccount
        var anim1 = AnimationUtils.loadAnimation(this,R.anim.from_top_to_bottom)
        binding.logBox?.startAnimation(anim1);
        //Model
        loginViewModel = LoginViewModel(this@LoginActivity);
        //Login Button
        login.setOnClickListener{
            loading.visibility = View.VISIBLE
           var result =  loginViewModel.login(username.text.toString(), password = password.text.toString()){
               loading.visibility = View.GONE
           }
            Log.d("Return from login view",result.toString());
            //loading.visibility = View.GONE
        }
        //To Register
        toSignin?.setOnClickListener {
            var intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
//
    }

    private fun updateUiWithUser() {
        val welcome = getString(R.string.welcome)
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}
