package com.terentiev.recipeplanner

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class RecipePlanner : Application() {

    companion object {
        const val USER_TOKEN = "token"
        const val USER_NAME = "username"
        lateinit var instance: RecipePlanner
    }

    private lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        instance = this
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
//        setUserToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiaWF0IjoxNTkxMjY4MDczfQ.DDrCNpCR-PBs0uqg8YqEL61BnrE0EioZoJ4kCF-LvIY")
    }

    fun getUserToken() = preferences.getString(USER_TOKEN, "")

    fun getUsername() = preferences.getString(USER_NAME, "")

    fun setUserToken(token: String) {
        preferences.edit().putString(USER_TOKEN, "Bearer $token").apply()
    }

    fun setUsername(username: String) {
        preferences.edit().putString(USER_NAME, username).apply()
    }


}