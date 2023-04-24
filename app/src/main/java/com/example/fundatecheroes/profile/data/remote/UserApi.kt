package com.example.fundatecheroes.profile.data.remote

import com.example.fundatecheroes.login.data.response.UserResponse
import com.example.fundatecheroes.profile.data.remote.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("api/login")
    suspend fun saveUser(
        @Body userRequest : UserRequest
    ): Response<UserResponse?>
}