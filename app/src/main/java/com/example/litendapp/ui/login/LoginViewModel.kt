package com.example.litendapp.ui.login


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import android.util.Patterns
import android.widget.Toast
import com.example.litendapp.data.Result
import com.example.litendapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel(var context:Context) : ViewModel() {

    fun login(username: String, password: String,closeLoading:()->Unit):Boolean {
        return if(!isUserNameValid(username)) {
            Toast.makeText(context, "Invalid Username", Toast.LENGTH_LONG).show()
            closeLoading()
            return false;
        }
        else if(!isPasswordValid(password))
        {
            Toast.makeText(context, "Invalid password", Toast.LENGTH_LONG).show()
            closeLoading()
            return false;
        }
        else{
            var isTrue :Boolean=false
            var auth = Firebase.auth
            auth.signInWithEmailAndPassword(username,password).
                addOnCompleteListener(){task->
                    if(task.isSuccessful){
                        Log.d("Login","login successful")
                        isTrue = true

                    }
                    else{
                        Toast.makeText(context,task.exception?.message,Toast.LENGTH_LONG).show()
                    }
                    closeLoading()
                }.addOnFailureListener{
                    Log.d("localized Message",it.message.toString());
            }
            isTrue
        }
    }

    fun loginDataChanged(username: String, password: String) {

    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
//            username.isNotBlank()
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}