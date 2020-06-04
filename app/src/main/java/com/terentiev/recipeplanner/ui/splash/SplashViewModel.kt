package com.terentiev.recipeplanner.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.terentiev.recipeplanner.data.AppRepository

class SplashViewModel : ViewModel() {
    private var repository = AppRepository.getInstance()
    private var authResult = MutableLiveData<Boolean>()

    fun checkToken() {
        repository.checkToken(authResult);
    }

    fun getAuthResultLiveData() = authResult
}