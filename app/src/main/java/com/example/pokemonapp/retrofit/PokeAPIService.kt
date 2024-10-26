package com.example.pokemonapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.example.pokemonapp.models.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

//==================================================================================================

interface PokeAPIService {

    // This endpoint fetches the details for a specific Pokémon by name
    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Pokemon
}


object RetrofitInstance {
    val api: PokeAPIService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeAPIService::class.java)
    }
}
//==================================================================================================
