package com.jlmari.android.basepokedex.networkdatasource

import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.networkdatasource.client.NetworkClient
import com.jlmari.android.basepokedex.networkdatasource.service.ApiService
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    networkClient: NetworkClient
) : NetworkDataSource {

    private val apiService: ApiService = networkClient.service

    override suspend fun getPokemons(): Response<List<String>, ErrorModel> {
        TODO("Not yet implemented. Response should be a list of PokemonModel")
    }
}
