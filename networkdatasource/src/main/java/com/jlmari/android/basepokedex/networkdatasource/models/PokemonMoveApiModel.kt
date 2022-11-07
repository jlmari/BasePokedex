package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class PokemonMoveApiModel(
    @SerializedName("move")
    val moveInfo: PokemonMoveInfoApiModel
)
