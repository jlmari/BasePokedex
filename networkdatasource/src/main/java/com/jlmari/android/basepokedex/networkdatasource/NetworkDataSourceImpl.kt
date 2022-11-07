package com.jlmari.android.basepokedex.networkdatasource

import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.networkdatasource.client.NetworkClient
import com.jlmari.android.basepokedex.networkdatasource.mappers.PokemonDetailOutApiMapper
import com.jlmari.android.basepokedex.networkdatasource.mappers.PokemonListOutApiMapper
import com.jlmari.android.basepokedex.networkdatasource.service.ApiService
import com.jlmari.android.basepokedex.networkdatasource.utils.safeApiCall
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    networkClient: NetworkClient,
    private val pokemonListOutApiMapper: PokemonListOutApiMapper,
    private val pokemonDetailOutApiMapper: PokemonDetailOutApiMapper
) : NetworkDataSource {

    private val apiService: ApiService by lazy {
        networkClient.builder.create(ApiService::class.java)
    }

    override suspend fun getPokemons(
        offset: Int,
        limit: Int
    ): Response<List<PokemonModel>, ErrorModel> =
        safeApiCall { pokemonListOutApiMapper.map(apiService.getPokemons(offset, limit).pokemonList) }

    override suspend fun getPokemonDetail(id: Int): Response<PokemonDetailModel, ErrorModel> =
        safeApiCall { pokemonDetailOutApiMapper.map(apiService.getPokemonDetail(id)) }
}
