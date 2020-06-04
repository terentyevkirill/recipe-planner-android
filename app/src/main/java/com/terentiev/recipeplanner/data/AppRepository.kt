package com.terentiev.recipeplanner.data

import androidx.lifecycle.MutableLiveData
import com.terentiev.recipeplanner.data.retrofit.RetrofitController

class AppRepository {

    companion object {
        private var instance: AppRepository? = null
        fun getInstance(): AppRepository {
            if (instance == null) {
                instance = AppRepository()
            }
            return instance as AppRepository
        }
    }

    private var retrofit: RetrofitController = RetrofitController()

    fun checkToken(authResult: MutableLiveData<Boolean>) {
        retrofit.checkToken(authResult)
    }

    fun loginAttempt(username: String, password: String, loginResult: MutableLiveData<Boolean>) {
        retrofit.loginAttempt(username, password, loginResult)
    }

}
