package com.example.fundatecheroes.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.profile.domain.usecase.UserUsecase
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    private val usecase: UserUsecase by lazy {
        UserUsecase()
    }

    fun validateAndSaveUser(name: String?, email: String?, password: String?) {
        viewModelScope.launch {
            state.value = ViewState.Loading
            if (!name.isNullOrEmpty() && !email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                if (!email.contains("@")) {
                    state.value = ViewState.ShowErrorEmail
                } else{
                    usecase.saveNewUser(name, email, password)?.let {
                        state.value = ViewState.ShowSuccess
                    }
                }
            } else
                state.value = ViewState.ShowErrorNull
        }
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorEmail : ViewState()
    object ShowSuccess : ViewState()
    object Loading : ViewState()
}