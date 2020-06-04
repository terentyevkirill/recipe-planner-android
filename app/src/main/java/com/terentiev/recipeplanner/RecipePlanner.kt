package com.terentiev.recipeplanner

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

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