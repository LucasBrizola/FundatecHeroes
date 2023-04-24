package com.example.fundatecheroes.character.response

data class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val universeType: String,
    val characterType: String,
    val age: Int,
    val birthday: String?
)
{
    enum class MarvelDC { MARVEL, DC }
    enum class HeroVillain { HERO, VILLAIN}
}