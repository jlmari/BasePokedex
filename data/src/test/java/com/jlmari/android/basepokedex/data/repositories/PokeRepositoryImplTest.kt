package com.jlmari.android.basepokedex.data.repositories

import com.jlmari.android.basepokedex.data.datasources.MemoryDataSource
import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.utils.Failure
import com.jlmari.android.basepokedex.domain.utils.Success
import com.jlmari.android.basepokedex.domain.utils.getOrThrow
import io.mockk.*
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
    private lateinit var memoryDataSource: MemoryDataSource

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

    private fun mockGetPokemonDetailSuccessFromApiResponse() {
        coEvery { memoryDataSource.getPokemonDetail(any()) } returns Failure(ErrorModel())
        coEvery { networkDataSource.getPokemonDetail(any()) } returns Success(pokemonDetail)
        coEvery { memoryDataSource.savePokemonDetail(any()) } returns Success(Unit)
    }

    private fun mockGetPokemonDetailSuccessFromDbResponse() {
        coEvery { memoryDataSource.getPokemonDetail(any()) } returns Success(pokemonDetail)
    }

    @Test
    fun `Call Network Data Source to get pokemon list with correct parameters when getPokemons() invoked`() {
        val inputOffset = 45
        val inputLimit = 15
        mockGetPokemonsSuccessResponse()

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
    fun `Call Network Data Source to get pokemon detail (and store it in Memory Data Source) with correct id when getPokemonDetail() invoked without being stored previously in Memory`() {
        val pokemonId = 33
        mockGetPokemonDetailSuccessFromApiResponse()

        runBlocking { pokeRepositoryImpl.getPokemonDetail(pokemonId) }

        coVerifyOrder {
            memoryDataSource.getPokemonDetail(pokemonId)
            networkDataSource.getPokemonDetail(pokemonId)
            memoryDataSource.savePokemonDetail(pokemonDetail)
        }
    }

    @Test
    fun `Return the expected PokemonDetailModel given by Network Data Source when getPokemonDetail() invoked without being stored previously in Memory`() {
        mockGetPokemonDetailSuccessFromApiResponse()

        val obtainedPokemonDetail = runBlocking { pokeRepositoryImpl.getPokemonDetail(1) }.getOrThrow()

        assertEquals(pokemonDetail, obtainedPokemonDetail)
    }

    @Test
    fun `Call Memory Data Source to get pokemon detail (and not from Network Data Source) with correct id when getPokemonDetail() invoked being stored previously in Memory`() {
        val pokemonId = 33
        mockGetPokemonDetailSuccessFromDbResponse()

        runBlocking { pokeRepositoryImpl.getPokemonDetail(pokemonId) }

        coVerify(exactly = 1) { memoryDataSource.getPokemonDetail(pokemonId) }
        coVerify(exactly = 0) { networkDataSource.getPokemonDetail(pokemonId) }
    }

    @Test
    fun `Return the expected PokemonDetailModel given by Memory Data Source when getPokemonDetail() invoked being stored previously in Memory`() {
        mockGetPokemonDetailSuccessFromDbResponse()

        val obtainedPokemonDetail = runBlocking { pokeRepositoryImpl.getPokemonDetail(1) }.getOrThrow()

        assertEquals(pokemonDetail, obtainedPokemonDetail)
    }
}
