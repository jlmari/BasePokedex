package com.jlmari.android.basepokedex.domain.usecases

import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repository: PokeRepository) {

    suspend operator fun invoke(offset: Int, limit: Int) = repository.getPokemons(offset, limit)
}
