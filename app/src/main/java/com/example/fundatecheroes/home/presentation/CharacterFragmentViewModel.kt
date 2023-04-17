package com.example.fundatecheroes.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.character.response.CharacterResponse
import com.example.fundatecheroes.character.usecase.CharacterUsecase
import com.example.fundatecheroes.profile.domain.usecase.UserUsecase
import kotlinx.coroutines.launch

class CharacterFragmentViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    private val characterUseCase: CharacterUsecase by lazy {
        CharacterUsecase()
    }

    private val userUseCase: UserUsecase by lazy {
        UserUsecase()
    }

    fun populateRecyclerView(heroType: CharacterResponse.HeroVillain) {
        viewModelScope.launch {
            state.value = ViewState.Loading
            val characterList: List<CharacterResponse> =
                characterUseCase.listAll(userUseCase.getUserId())
                    .filter {
                        it.characterType == heroType.name
                    }
            Log.e("CharacterDataSource", "characterList: " + "${characterList}")
            if (characterList.isEmpty()) {
                state.value = ViewState.ShowListEmpty
            } else
                state.value = ViewState.ShowCharacterList(characterList)
        }
    }
}

sealed class ViewState {
    data class ShowCharacterList(val characterList: List<CharacterResponse>) : ViewState()
    object ShowListEmpty : ViewState()
    object Loading : ViewState()
}