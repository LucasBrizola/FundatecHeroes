package com.example.fundatecheroes.profile.data.local

import android.content.Context
import com.example.fundatecheroes.App
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class LocalDatasource {

    private val moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    val preferences = App.context.getSharedPreferences("bd", Context.MODE_PRIVATE)

    fun getUserId(): Int {
        val userString = preferences.getString("user", "")
        val characterFromPreferences: User = moshi.adapter(User::class.java)
            .fromJson(userString)!!
        return characterFromPreferences.id
    }

    fun saveLogin(id: Int, name: String, email: String, password: String) {
        val user = User(id, name, email, password)
        val userString = moshi.adapter(User::class.java).toJson(user)
        preferences.edit().putString("user", userString).commit()
    }
}