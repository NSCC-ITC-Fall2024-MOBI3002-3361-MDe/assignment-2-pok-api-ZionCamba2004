package com.example.pokemonapp.models

data class Pokemon(
    val name: String,
    val types: List<Type>,
    val height: Int,
    val weight: Int,
    val sprites: Sprites
)

data class Type(
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class Sprites(
    val front_default: String
)



