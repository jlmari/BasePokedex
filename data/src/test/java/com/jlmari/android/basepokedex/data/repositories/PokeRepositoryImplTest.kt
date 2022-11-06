package com.jlmari.android.basepokedex.data.repositories

import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
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

internal class PokeRepositoryImplTest {

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    @MockK
    private lateinit var pokemonList: List<PokemonModel>

    @MockK
    private lateinit var pokemonDetail: PokemonDetailModel

    @InjectMockKs
    private lateinit var pokeRepositoryImpl: PokeRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    private fun mockGetPokemonsSuccessResponse() {
        coEvery { networkDataSource.getPokemons(any(), any()) } returns Success(pokemonList)
    }

    private fun mockGetPokemonDetailSuccessResponse() {
        coEvery { networkDataSource.getPokemonDetail(any()) } returns Success(pokemonDetail)
    }

    @Test
    fun `Call Network Data Source to get pokemon list with correct parameters when getPokemons() invoked`() {
        mockGetPokemonsSuccessResponse()

        val inputOffset = 45
        val inputLimit = 15
        runBlocking { pokeRepositoryImpl.getPokemons(inputOffset, inputLimit) }

        coVerify(exactly = 1) { networkDataSource.getPokemons(inputOffset, inputLimit) }
    }

    @Test
    fun `Return the expected PokemonModel list given by Network Data Source when getPokemons() invoked`() {
        mockGetPokemonsSuccessResponse()

        val obtainedPokemonList = runBlocking { pokeRepositoryImpl.getPokemons(0, 0) }.getOrThrow()

        assertEquals(pokemonList, obtainedPokemonList)
    }

    @Test
    fun `Call Network Data Source to get pokemon detail with correct id when getPokemonDetail() invoked`() {
        mockGetPokemonDetailSuccessResponse()

        val pokemonId = 33
        runBlocking { pokeRepositoryImpl.getPokemonDetail(pokemonId) }

        coVerify(exactly = 1) { networkDataSource.getPokemonDetail(pokemonId) }
    }

    @Test
    fun `Return the expected PokemonDetailModel given by Network Data Source when getPokemonDetail() invoked`() {
        mockGetPokemonDetailSuccessResponse()

        val obtainedPokemonDetail = runBlocking { pokeRepositoryImpl.getPokemonDetail(1) }.getOrThrow()

        assertEquals(pokemonDetail, obtainedPokemonDetail)
    }
}
