package com.example.fundatecheroes.login.data.domain.usecase

import com.example.fundatecheroes.login.data.remote.LoginDataSource
import com.example.fundatecheroes.login.data.response.LoginResponse
import com.example.fundatecheroes.profile.data.repository.LoginRepository

class LoginUsecase {

    private val repository: LoginRepository by lazy {
        LoginRepository()
    }

    private val loginDataSource: LoginDataSource by lazy {
        LoginDataSource()
    }

    suspend fun saveUserAfterLogin(email: String, password: String) {
        val loginResponse = loginDataSource.login(email, password)
        repository.saveLogin(loginResponse!!)
    }

}
