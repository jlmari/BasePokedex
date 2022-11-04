package com.jlmari.android.basepokedex.data.datasources

import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.utils.Response

interface NetworkDataSource {

    suspend fun getPokemons(): Response<List<PokemonModel>, ErrorModel>
}
