package com.jlmari.android.basepokedex.data.repositories

import com.jlmari.android.basepokedex.data.datasources.MemoryDataSource
import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.data.utils.singleSourceOfTruth
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.domain.utils.Success
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val memoryDataSource: MemoryDataSource
) : PokeRepository {

    override suspend fun getPokemons(
        offset: Int,
        limit: Int
    ): Response<List<PokemonModel>, ErrorModel> =
        networkDataSource.getPokemons(offset, limit)

    override suspend fun getPokemonDetail(id: Int): Response<PokemonDetailModel, ErrorModel> =
        singleSourceOfTruth(
            memoryDataSource = { memoryDataSource.getPokemonDetail(id) },
            networkDataSource = { networkDataSource.getPokemonDetail(id) },
            memoryCallback = { pokemonDetail ->
                memoryDataSource.savePokemonDetail(pokemonDetail)
                Success(pokemonDetail)
            }
        )
}
