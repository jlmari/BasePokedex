package com.jlmari.android.basepokedex.data.repositories

import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
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

    @InjectMockKs
    private lateinit var pokeRepositoryImpl: PokeRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `Call Network Data Source to get pokemon list when getPokemons() invoked`() {
        coEvery { networkDataSource.getPokemons(,) } returns Success(pokemonList)

        runBlocking { pokeRepositoryImpl.getPokemons(,) }

        coVerify(exactly = 1) { networkDataSource.getPokemons(,) }
    }

    @Test
    fun `Return the expected PokemonModel list given by Network Data Source when getPokemons() invoked`() {
        coEvery { networkDataSource.getPokemons(,) } returns Success(pokemonList)

        val obtainedPokemonList = runBlocking { pokeRepositoryImpl.getPokemons(,) }.getOrThrow()

        assertEquals(pokemonList, obtainedPokemonList)
    }
}
