package com.jlmari.android.basepokedex.domain.usecases

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val repository: PokeRepository
) {

    suspend operator fun invoke(id: Int) =
        withContext(appDispatchers.io) { repository.getPokemonDetail(id) }
}
