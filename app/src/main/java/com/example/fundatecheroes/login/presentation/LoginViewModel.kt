package com.example.fundatecheroes.login.view.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val newTextMutable = MutableLiveData<String>()
    val newText: LiveData<String> = newTextMutable

    fun changeText(newText: String) {
        newTextMutable.value = newText
    }
}