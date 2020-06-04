package com.terentiev.recipeplanner.data.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.terentiev.recipeplanner.RecipePlanner
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitController {

    private val BASE_URL = "https://recipe-planner-app.herokuapp.com/"

    private var client: RetrofitClient
    private var coroutineJob: Job? = null

    private fun buildRetrofit(): Retrofit {
        val gson = GsonBuilder().serializeNulls().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    init {
        client = buildRetrofit().create(RetrofitClient::class.java)
    }

    fun loginAttempt(username: String, password: String, loginResult: MutableLiveData<Boolean>) {
        Log.d("RetrofitController", "loginAttempt(username: $username, password: $password)")
        coroutineJob = CoroutineScope(Dispatchers.IO).launch {
            val response = client.login(AuthRequest(username, password))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("RetrofitController", "Logged in")
                    loginResult.postValue(true)
                    RecipePlanner.instance.setUserToken(response.body()!!.token)
                    RecipePlanner.instance.setUsername(response.body()!!.username)
                } else {
                    Log.d("RetrofitController", "Could not log in")
                    loginResult.postValue(false)
                }
            }
        }
    }

    fun checkToken(authResult: MutableLiveData<Boolean>) {
        val token = RecipePlanner.instance.getUserToken()
        if (token != null && token != "") {
            coroutineJob = CoroutineScope(Dispatchers.IO).launch {
                val response = client.checkToken(token)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("RetrofitController", "Authenticated")
                        authResult.postValue(true)
                    } else {
                        Log.d("RetrofitController", "Not Authenticated: illegal token")
                        RecipePlanner.instance.setUserToken("")
                        RecipePlanner.instance.setUsername("")
                        authResult.postValue(false)
                    }
                }
            }
        } else {
            Log.d("RetrofitController", "Not Authenticated: empty token")
            authResult.postValue(false)
        }
    }
}