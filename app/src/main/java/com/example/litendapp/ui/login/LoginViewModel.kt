package com.example.litendapp.ui.login


import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.litendapp.data.Result
import com.example.litendapp.R

class LoginViewModel() : ViewModel() {

    fun login(username: String, password: String) {

    }

    fun loginDataChanged(username: String, password: String) {

    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}