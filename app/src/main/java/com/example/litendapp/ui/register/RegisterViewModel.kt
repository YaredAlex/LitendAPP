package com.example.litendapp.ui.register

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.litendapp.data.model.RegisterUserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class RegisterViewModel(var context:Context) {
    fun register(user:RegisterUserModel){
        if(!isNameValid(user.userName))
            return
        else if(!isEmailValid(user.email))
            return
        else if(!isPasswordValid(user.password))
            return
        else if(!isPhoneValid(user.phoneNumber))
            return
        var auth = Firebase.auth
        auth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener {task->

            if(task.isSuccessful){

            }
        }.addOnFailureListener{
            Toast.makeText(context,"Registration Failed Successfully!!",Toast.LENGTH_LONG).show()
            Toast.makeText(context, it.localizedMessage?.toString() ,Toast.LENGTH_LONG).show();
        }
    }
    fun isNameValid(name:String):Boolean{
        return if(name.length<2){
            Toast.makeText(context,"Invalid user name",Toast.LENGTH_LONG).show()
          false
             }
            else if(Pattern.compile("[^a-z0-9]",Pattern.CASE_INSENSITIVE).matcher(name).find()){
                Toast.makeText(context,"special characters are not allowed as user name (@#$%^&!)",Toast.LENGTH_LONG).show()
                 false
                }
            else
            true
    }
    fun isEmailValid(email:String):Boolean{
        return if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(context,"Invalid Email Address",Toast.LENGTH_SHORT).show()
            false
        }
        else
            true
    }
    fun isPasswordValid(password:String):Boolean{
        return if(password.length<6){
            Toast.makeText(context,"Password is short < 6",Toast.LENGTH_LONG).show()
            false
        }
        else if(!Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{6,})").matcher(password).matches()){
            Toast.makeText(context,"Password is Weak",Toast.LENGTH_LONG).show()
            false
        }
        else
            true
    }
    fun isPhoneValid(phone:String):Boolean{
        return if(!Patterns.PHONE.matcher(phone).matches()){
            Toast.makeText(context,"Invalid Phone Number",Toast.LENGTH_LONG).show()
            false
        }
        else
            true
    }
}