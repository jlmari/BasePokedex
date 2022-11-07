package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class PokemonSpritesApiModel(
    @SerializedName("back_default")
    val backDefaultPhoto: String,
    @SerializedName("front_default")
    val frontDefaultPhoto: String
)
