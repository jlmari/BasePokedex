package com.jlmari.android.basepokedex.networkdatasource.service

import com.jlmari.android.basepokedex.networkdatasource.models.GetPokemonsResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("${API_VERSION}/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): GetPokemonsResponseApiModel

    companion object {
        private const val API_VERSION = "api/v2"
    }
}
