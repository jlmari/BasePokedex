package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class GetPokemonsResponseApiModel(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val nextUrl: String?,
    @SerializedName("previous")
    val previousUrl: String?,
    @SerializedName("results")
    val pokemonList: List<PokemonApiModel>
)
