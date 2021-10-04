package com.example.soyrobado.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soyrobado.firebase.FirebaseRepository

class LoginViewModel(val repository: FirebaseRepository) :ViewModel() {

    var logindatasource = repository.getUserDataSource()
    var loginMessage = MutableLiveData<String>()


    init {
    loginMessage = logindatasource.loginMessage

    }

    fun login(user: String, pass: String) {
        logindatasource.requestLogin(user, pass)
    }


}