package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class PokemonAbilityApiModel(
    @SerializedName("ability")
    val abilityInfo: PokemonAbilityInfoApiModel
)
