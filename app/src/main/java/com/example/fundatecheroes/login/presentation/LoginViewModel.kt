package com.example.fundatecheroes.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.login.data.domain.usecase.LoginUsecase
import com.example.fundatecheroes.login.data.remote.LoginDataSource
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    private val loginUsecase: LoginUsecase by lazy {
        LoginUsecase()
    }

    fun validarEmailESenha(email: String?, password: String?) {
        viewModelScope.launch {
            state.value = ViewState.Loading
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                val user = LoginDataSource().login(email, password)
                if (user != null) {
                    if (!email.contains("@")) {
                        state.value = ViewState.ShowErrorEmail
                    } else
                        loginUsecase.saveUserAfterLogin(email, password)
                    state.value = ViewState.ShowSuccess
                } else
                    state.value = ViewState.ShowErrorUserNull
            } else
                state.value = ViewState.ShowErrorNull
        }
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorEmail : ViewState()
    object ShowSuccess : ViewState()
    object ShowErrorUserNull : ViewState()
    object Loading : ViewState()
}