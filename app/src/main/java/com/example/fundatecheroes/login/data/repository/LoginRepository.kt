package com.example.fundatecheroes.profile.data.repository

import com.example.fundatecheroes.login.data.response.LoginResponse
import com.example.fundatecheroes.profile.data.local.LocalDatasource

class LoginRepository {

    private val localDatasource: LocalDatasource by lazy{
        LocalDatasource()
    }

    fun saveLogin(login: LoginResponse) {
        return localDatasource.saveLogin(login.id, login.name, login.email, login.password)
    }
}