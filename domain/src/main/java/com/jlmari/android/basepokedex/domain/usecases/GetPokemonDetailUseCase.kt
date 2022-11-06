package com.jlmari.android.basepokedex.domain.usecases

import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: PokeRepository) {

    suspend operator fun invoke(id: Int) = repository.getPokemonDetail(id)
}
