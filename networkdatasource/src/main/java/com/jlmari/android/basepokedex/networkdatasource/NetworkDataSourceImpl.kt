package com.jlmari.android.basepokedex.networkdatasource

import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.networkdatasource.client.NetworkClient
import com.jlmari.android.basepokedex.networkdatasource.mappers.PokemonMapper
import com.jlmari.android.basepokedex.networkdatasource.service.ApiService
import com.jlmari.android.basepokedex.networkdatasource.utils.safeApiCall
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    networkClient: NetworkClient,
    private val pokemonMapper: PokemonMapper
) : NetworkDataSource {

    private val apiService: ApiService by lazy {
        networkClient.builder.create(ApiService::class.java)
    }

    override suspend fun getPokemons(offset: Int, limit: Int): Response<List<PokemonModel>, ErrorModel> {
        return safeApiCall { pokemonMapper.map(apiService.getPokemons(offset, limit).pokemonList) }
    }
}
