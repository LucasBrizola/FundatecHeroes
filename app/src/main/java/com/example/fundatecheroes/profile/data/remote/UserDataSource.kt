package com.example.fundatecheroes.profile.data.remote

import android.util.Log
import com.example.fundatecheroes.login.data.response.UserResponse
import com.example.fundatecheroes.webservice.RetrofitNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource {

    private val service = RetrofitNetworkClient
        .createNetworkClient()
        .create(UserApi::class.java)

    suspend fun saveUser(userRequest: UserRequest): UserResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val userResponse = service.saveUser(userRequest)
                if (userResponse.isSuccessful) {
                    userResponse.body()
                } else {
                    null
                }
            } catch (ex: Exception) {
                Log.e("UserDataSource", ex.message ?: "usuário não econtrado")
                null
            }
        }
    }
}