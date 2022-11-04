package com.jlmari.android.basepokedex.data.repositories

import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import com.jlmari.android.basepokedex.domain.utils.Response
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
): PokeRepository {

    override suspend fun getPokemons(): Response<List<PokemonModel>, ErrorModel> {
        return networkDataSource.getPokemons()
    }
}
