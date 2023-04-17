package com.example.fundatecheroes.profile.domain.usecase

import com.example.fundatecheroes.login.data.remote.LoginDataSource
import com.example.fundatecheroes.login.data.remote.UserDataSource
import com.example.fundatecheroes.profile.data.local.LocalDatasource
import com.example.fundatecheroes.profile.data.local.User
import com.example.fundatecheroes.profile.data.remote.UserRequest

class UserUsecase {
    
    private val userDataSource: UserDataSource by lazy {
        userDataSource
    }

    private val localDatasource: LocalDatasource by lazy {
        LocalDatasource()
    }

    suspend fun saveNewUser(name: String, email: String, password: String) {
        userDataSource.saveUser(UserRequest(name, email, password))
    }

    fun getUserId(): Int {
        return localDatasource.getUserId()
    }

}