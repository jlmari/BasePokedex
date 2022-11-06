package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class PokemonTypeApiModel(
    @SerializedName("type")
    val typeInfo: PokemonTypeInfoApiModel
) {
    data class PokemonTypeInfoApiModel(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val detailUrl: String
    )
}
