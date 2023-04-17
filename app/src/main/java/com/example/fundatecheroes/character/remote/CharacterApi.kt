package com.example.fundatecheroes.character.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.fundatecheroes.character.data.Character
import com.example.fundatecheroes.character.response.CharacterResponse

interface CharacterApi {

    @GET("api/character/{userId}")
    suspend fun getAllCharacters(
        @Path("userId") userId: Int,
    ) : Response<List<CharacterResponse>>

    @POST("api/character/{userId}")
    suspend fun saveCharacter(
        @Path("userId") userId: Int,
        @Body character: Character
    ) : Response<Int?>
}