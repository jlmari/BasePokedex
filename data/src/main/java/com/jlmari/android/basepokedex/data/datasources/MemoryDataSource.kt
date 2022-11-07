package com.jlmari.android.basepokedex.data.datasources

import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.utils.Response

interface MemoryDataSource {

    suspend fun getPokemonDetail(id: Int): Response<PokemonDetailModel, ErrorModel>

    suspend fun savePokemonDetail(pokemonDetail: PokemonDetailModel): Response<Unit, ErrorModel>
}
