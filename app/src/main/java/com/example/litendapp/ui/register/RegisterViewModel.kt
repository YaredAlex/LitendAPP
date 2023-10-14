package com.example.litendapp.ui.register

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.litendapp.data.VolleySingleton
import com.example.litendapp.data.model.RegisterUserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.util.regex.Pattern


class RegisterViewModel(var context:Context) {

     var finished = MutableLiveData<Int>(-1)
    fun register(user:RegisterUserModel){
        if(!isNameValid(user.userName))
            return
        else if(!isEmailValid(user.email))
            return
        else if(!isPasswordValid(user.password))
            return
        else if(!isPhoneValid(user.phoneNumber))
            return
        else
        addUserToDB(user)
//         firebaseAuth(user)

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
        else if(!Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{6,}").matcher(password).matches()){
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
    fun firebaseAuth(user:RegisterUserModel){
        var auth = Firebase.auth
        auth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener {task->

            if(task.isSuccessful){
                finished.value = 2
            }
        }.addOnFailureListener{
            finished.value = 1
            Toast.makeText(context,"Registration Failed Successfully!!",Toast.LENGTH_LONG).show()
            Toast.makeText(context, it.localizedMessage?.toString() ,Toast.LENGTH_LONG).show();
        }
    }
    fun addUserToDB(user:RegisterUserModel){
        finished.value = 0
        var url = "https://litendapp.onrender.com/admin/users/add"
//        var url = "http://192.168.0.105:8080/admin/users/add"
        var jsonObjectRequest = object :JsonObjectRequest(Method.POST,"$url",null, { respose->
            Log.d("response",respose.toString())
            Toast.makeText(context,respose.getString("message"),Toast.LENGTH_LONG).show()
            if(respose.getString("message").equals("success")){
                firebaseAuth(user)
            }
            else
              finished.value = 1;
        }, { error->
            Log.d("Error",error.message.toString())
            Toast.makeText(context,error.message.toString(),Toast.LENGTH_LONG).show()
            finished.value = 1

        }){
            override fun getParams(): MutableMap<String, String>? {
                var record = mutableMapOf<String,String>()
                record.put("name",user.userName)
                record.put("phone",user.phoneNumber)
                return record
            }

            override fun getBody(): ByteArray {
                var json  = JSONObject()
                json.put("name",user.userName)
                json.put("email",user.email)
                json.put("phone",user.phoneNumber)
                var ss = json.toString()
                return ss.toByteArray()
            }
            override fun getHeaders(): MutableMap<String, String> {
                Log.d("Header", "Does it assign headers?")
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }
        }
         VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }
}