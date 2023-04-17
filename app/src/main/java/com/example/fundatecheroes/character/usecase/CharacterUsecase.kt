package com.example.fundatecheroes.character.usecase

import com.example.fundatecheroes.character.remote.RemoteCharacterDataSource
import com.example.fundatecheroes.character.response.CharacterResponse

class CharacterUsecase {

    private val remoteCharacterDataSource: RemoteCharacterDataSource by lazy {
        RemoteCharacterDataSource()
    }

    suspend fun listAll(id: Int): List<CharacterResponse>{
        return remoteCharacterDataSource.getListAll(id)

    }

    suspend fun saveCharacter(
        userId: Int, name: String, url: String, description: String,
        marvelDc: String, heroiVilao: String, age: Int, birthday: String?
    ) {
        remoteCharacterDataSource.saveCharacterApi(userId, name, url, description,
            marvelDc, heroiVilao, age, birthday)

    }
}