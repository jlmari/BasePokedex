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

    @Test
    fun `Call PokeRepository to get pokemons when invoked`() {
        coEvery { pokeRepository.getPokemons() } returns Success(pokemonList)

        runBlocking { getPokemonsUseCase.invoke() }

        coVerify(exactly = 1) { pokeRepository.getPokemons() }
    }

    @Test
    fun `Return the expected List of PokemonModel by PokeRepository when invoked`() {
        coEvery { pokeRepository.getPokemons() } returns Success(pokemonList)

        val obtainedPokemonList = runBlocking { getPokemonsUseCase.invoke() }.getOrThrow()

        assertEquals(pokemonList, obtainedPokemonList)
    }
}
