package com.jlmari.android.basepokedex.domain.usecases

import com.jlmari.android.basepokedex.domain.models.PokemonModel
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

internal class GetPokemonsUseCaseTest {

    @MockK
    private lateinit var pokeRepository: PokeRepository

    @MockK
    private lateinit var pokemonList: List<PokemonModel>

    @InjectMockKs
    private lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    private fun mockGetPokemonsSuccessResponse() {
        coEvery { pokeRepository.getPokemons(any(), any()) } returns Success(pokemonList)
    }

    @Test
    fun `Call PokeRepository to get pokemons with correct parameters when invoked`() {
        val inputOffset = 60
        val inputLimit = 20
        mockGetPokemonsSuccessResponse()

        runBlocking { getPokemonsUseCase.invoke(inputOffset, inputLimit) }

        coVerify(exactly = 1) { pokeRepository.getPokemons(inputOffset, inputLimit) }
    }

    @Test
    fun `Return the expected List of PokemonModel by PokeRepository when invoked`() {
        mockGetPokemonsSuccessResponse()

        val obtainedPokemonList = runBlocking { getPokemonsUseCase.invoke(0, 0) }.getOrThrow()

        assertEquals(pokemonList, obtainedPokemonList)
    }
}
