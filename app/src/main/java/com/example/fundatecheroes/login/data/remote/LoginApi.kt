package com.example.fundatecheroes.login.data.remote

import com.example.fundatecheroes.login.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("api/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<LoginResponse>
}