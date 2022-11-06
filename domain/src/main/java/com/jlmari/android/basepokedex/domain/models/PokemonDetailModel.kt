package com.jlmari.android.basepokedex.domain.models

data class PokemonDetailModel(
    val id: Int,
    val order: Int,
    val name: String,
    val weightKg: Double,
    val heightMeters: Double,
    val types: List<String>,
    val photoUrls: List<String>,
    val abilities: List<String>,
    val moves: List<String>
)
