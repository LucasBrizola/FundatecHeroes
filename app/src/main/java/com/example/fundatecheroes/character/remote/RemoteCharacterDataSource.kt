package com.example.fundatecheroes.character.remote

import android.util.Log
import com.example.fundatecheroes.webservice.RetrofitNetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.fundatecheroes.character.data.Character
import com.example.fundatecheroes.character.response.CharacterResponse

class RemoteCharacterDataSource {
    private val service = RetrofitNetworkClient
        .createNetworkClient()
        .create(CharacterApi::class.java)

    suspend fun getListAll(userId: Int): List<CharacterResponse> {
        try {
            val characterResponse = service.getAllCharacters(userId)
            if (characterResponse.isSuccessful) {
                Log.e("CharacterDataSource", "body from response: " + "${characterResponse.body()}")
                return characterResponse.body()!!
            } else {
                return listOf()
            }
        } catch (ex: Exception) {
            Log.e("CharacterDataSource", ex.message ?: "personagem não salvo")
            return listOf()
        }
    }

    suspend fun saveCharacterApi(userId: Int, name: String, description: String, image: String,
                                 universeType: String, characterType: String, age: Int, birthday: String?
    ): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val userResponse = service.saveCharacter(userId, Character(name, description
                    , image, universeType, characterType, age, birthday)
                )
                if (userResponse.isSuccessful) {
                    userResponse.body()
                } else {
                    null
                }
            } catch (ex: Exception) {
                Log.e("CharacterDataSource", ex.message ?: "personagem não salvo")
                null
            }
        }
    }
}