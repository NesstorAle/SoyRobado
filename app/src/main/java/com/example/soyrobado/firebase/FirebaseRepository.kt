package com.example.soyrobado.firebase

import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository {

    val auth= FirebaseAuth.getInstance()

    fun getUserDataSource() = UserDataSource(auth)

    fun getDeviceDataSource() = DeviceDataSource()

}