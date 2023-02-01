package com.example.fundatecheroes.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state


    fun validarCampos(
        nome: String?, url: String?, descricao: String?,
        heroiVilao: String?, idade: String?, aniversario: String?
    ) {
        if (nome == null || url.isNullOrEmpty() || descricao.isNullOrEmpty()
            || heroiVilao.isNullOrEmpty() || idade.isNullOrEmpty() || aniversario.isNullOrEmpty()
        ) {
            state.value = ViewState.ShowErrorNull
            return
        }

        if (!url!!.contains("@")) {
            state.value = ViewState.ShowErrorUrl
        } else
            state.value = ViewState.ShowSuccess(character = Character(nome = nome, url = url, descricao = descricao, heroiVilao = heroiVilao, idade = idade, aniversario = aniversario))
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorUrl : ViewState()
    data class ShowSuccess(val character: Character) : ViewState()
}