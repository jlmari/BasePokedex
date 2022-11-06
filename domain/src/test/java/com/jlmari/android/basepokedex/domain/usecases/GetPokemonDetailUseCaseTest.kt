package com.jlmari.android.basepokedex.domain.usecases

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import com.jlmari.android.basepokedex.domain.utils.Success
import com.jlmari.android.basepokedex.domain.utils.getOrThrow
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class GetPokemonDetailUseCaseTest {

    @MockK
    private lateinit var pokeRepository: PokeRepository

    @MockK
    private lateinit var pokemonDetail: PokemonDetailModel

    @InjectMockKs
    private lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    private fun mockGetPokemonDetailSuccessResponse() {
        coEvery { pokeRepository.getPokemonDetail(any()) } returns Success(pokemonDetail)
    }

    @Test
    fun `Call PokeRepository to get pokemon detail with correct id when invoked`() {
        mockGetPokemonDetailSuccessResponse()

        val pokemonId = 61
        runBlocking { getPokemonDetailUseCase.invoke(pokemonId) }

        coVerify(exactly = 1) { pokeRepository.getPokemonDetail(pokemonId) }
    }

    @Test
    fun `Return the expected PokemonDetailModel by PokeRepository when invoked`() {
        mockGetPokemonDetailSuccessResponse()

        val obtainedPokemonDetail = runBlocking { getPokemonDetailUseCase.invoke(0) }.getOrThrow()

        assertEquals(pokemonDetail, obtainedPokemonDetail)
    }
}
