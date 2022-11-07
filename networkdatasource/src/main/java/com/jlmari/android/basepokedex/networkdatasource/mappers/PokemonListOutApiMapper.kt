package com.jlmari.android.basepokedex.networkdatasource.mappers

import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.utils.ListMapper
import com.jlmari.android.basepokedex.networkdatasource.models.PokemonApiModel
import javax.inject.Inject

class PokemonListOutApiMapper @Inject constructor() : ListMapper<PokemonApiModel, PokemonModel> {

    override fun map(from: PokemonApiModel): PokemonModel =
        PokemonModel(from.name, convertUrlToId(from.detailUrl))

    private fun convertUrlToId(url: String): Int =
        url.substringAfter("/pokemon/")
            .filter { substring -> substring.isDigit() }
            .toInt()
}
