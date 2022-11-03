package com.jlmari.android.basepokedex.networkdatasource.service

import com.jlmari.android.basepokedex.domain.models.EntityModel
import retrofit2.http.GET

interface ApiService {

    @GET("${API_VERSION}/pokemons")
    suspend fun getPokemons(): EntityModel

    companion object {
        private const val API_VERSION = "api/v2"
    }
}
