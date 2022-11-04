package com.jlmari.android.basepokedex.networkdatasource.service

import com.jlmari.android.basepokedex.networkdatasource.models.GetPokemonsResponseApiModel
import retrofit2.http.GET

interface ApiService {

    @GET("${API_VERSION}/pokemon")
    suspend fun getPokemons(): GetPokemonsResponseApiModel

    companion object {
        private const val API_VERSION = "api/v2"
    }
}
