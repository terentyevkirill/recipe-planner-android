package com.terentiev.recipeplanner.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terentiev.recipeplanner.data.AppRepository

class LoginViewModel : ViewModel() {
    private var repository = AppRepository.getInstance()

    private var loginResult = MutableLiveData<Boolean>()

    fun loginAttempt(username: String, password: String) {
        repository.loginAttempt(username, password, loginResult)
    }

    fun getLoginResultLiveData() = loginResult
}
