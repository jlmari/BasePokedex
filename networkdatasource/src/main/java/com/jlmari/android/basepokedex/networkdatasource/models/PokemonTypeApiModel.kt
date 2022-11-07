package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class PokemonTypeApiModel(
    @SerializedName("type")
    val typeInfo: PokemonTypeInfoApiModel
)
