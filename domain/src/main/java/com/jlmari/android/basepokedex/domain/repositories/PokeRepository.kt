package com.jlmari.android.basepokedex.domain.repositories

import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.utils.Response

interface PokeRepository {

    suspend fun getPokemons(): Response<List<String>, ErrorModel>
}
