package com.example.fundatecheroes.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state


    fun validarCampos(
        nome: String?, url: String?, descricao: String?,
        DcMarvel: String?, idade: String?, aniversario: String?
    ) {
        if (nome.isNullOrEmpty() || url.isNullOrEmpty() || descricao.isNullOrEmpty()
            || DcMarvel.isNullOrEmpty() || idade.isNullOrEmpty() || aniversario.isNullOrEmpty()
        ) {
            state.value = ViewState.ShowErrorNull
        }

        if (!url!!.contains("@")) {
            state.value = ViewState.ShowErrorUrl
        } else
            state.value = ViewState.ShowSuccess
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorUrl : ViewState()
    object ShowSuccess : ViewState()
}