package com.example.soyrobado.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class UserDataSource(val auth: FirebaseAuth) {
    var loginResult = MutableLiveData<Boolean>()
    var loginMessage = MutableLiveData<String>()


    fun requestLogin(user: String, pass: String) {
        auth.signInWithEmailAndPassword(user, pass).addOnCompleteListener { task ->

            if (task.isComplete) {
                if(task.exception != null){
                    //existe un error
                    loginMessage.postValue(task.exception?.localizedMessage.toString())
                    loginResult.postValue(false)
                }else{
                    if (task.result != null) {
                        loginResult.postValue(true)
                        loginMessage.postValue("Welcome "+task.result?.user?.email)
                        Log.i("ID-Firebase-User",task.result?.user?.uid.toString())
                    }
                }

            } else {
                loginMessage.postValue("Error al conectar")
                loginResult.postValue(false)
            }
        }

    }

}