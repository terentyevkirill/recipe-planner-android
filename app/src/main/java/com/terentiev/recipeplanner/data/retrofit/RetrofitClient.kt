package com.terentiev.recipeplanner.data.retrofit

import retrofit2.Response
import retrofit2.http.*

interface RetrofitClient {

    @POST("/users/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("/users/register")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>

    @GET("/users/auth")
    suspend fun checkToken(@Header("Authorization") token: String): Response<Void>

}