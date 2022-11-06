package com.jlmari.android.basepokedex.domain.repositories

import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.utils.Response

interface PokeRepository {

    suspend fun getPokemons(offset: Int, limit: Int): Response<List<PokemonModel>, ErrorModel>

    suspend fun getPokemonDetail(id: Int): Response<PokemonDetailModel, ErrorModel>
}
