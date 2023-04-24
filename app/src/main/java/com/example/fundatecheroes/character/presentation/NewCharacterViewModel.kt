package com.example.fundatecheroes.character.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.App
import com.example.fundatecheroes.character.data.Character
import com.example.fundatecheroes.character.usecase.CharacterUsecase
import com.example.fundatecheroes.profile.domain.usecase.UserUsecase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch


class NewCharacterViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    private val characterUseCase: CharacterUsecase by lazy {
        CharacterUsecase()
    }

    private val userUseCase: UserUsecase by lazy {
        UserUsecase()
    }


    fun validarCampos(
        name: String?, description: String?, url: String?, heroiVilao: String?,
        age: String?, birthday: String?
    ) {
        viewModelScope.launch {
            state.value = ViewState.Loading
            if (name == null || url.isNullOrEmpty() || description.isNullOrEmpty()
                || heroiVilao.isNullOrEmpty() || age.isNullOrEmpty()
            ) {
                state.value = ViewState.ShowErrorNull
            }
            if (heroiVilao.equals("Herói ou Vilão?")) {
                state.value = ViewState.ShowErrorHeroiVilao
            } else {
                val idUser = userUseCase.getUserId()
                characterUseCase.saveCharacter(
                    idUser, name!!, description!!, url!!, "MARVEL",
                    heroiVilao!!, age!!.toInt(), birthday
                )
                state.value = ViewState.ShowSuccess
            }
        }
    }
}

sealed class ViewState {
    object ShowErrorNull : ViewState()
    object ShowErrorHeroiVilao : ViewState()
    object Loading : ViewState()
    object ShowSuccess : ViewState()
}