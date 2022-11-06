package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class GetPokemonDetailResponseApiModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("order")
    val order: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("types")
    val typeList: List<PokemonTypeApiModel>,
    @SerializedName("sprites")
    val photoList: PokemonSpritesApiModel,
    @SerializedName("abilities")
    val abilityList: List<PokemonAbilityApiModel>,
    @SerializedName("moves")
    val moveList: List<PokemonMoveApiModel>
)
