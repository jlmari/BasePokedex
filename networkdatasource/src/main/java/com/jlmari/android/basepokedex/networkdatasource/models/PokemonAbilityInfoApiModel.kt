package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class PokemonAbilityInfoApiModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val detailUrl: String
)
