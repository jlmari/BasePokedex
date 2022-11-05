package com.jlmari.android.basepokedex.networkdatasource

import com.jlmari.android.basepokedex.networkdatasource.client.NetworkClient
import com.jlmari.android.basepokedex.networkdatasource.mappers.PokemonMapper
import com.jlmari.android.basepokedex.networkdatasource.models.GetPokemonsResponseApiModel
import com.jlmari.android.basepokedex.networkdatasource.models.PokemonApiModel
import com.jlmari.android.basepokedex.networkdatasource.service.ApiService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

internal class NetworkDataSourceImplTest {

    @MockK
    private lateinit var apiService: ApiService

    @MockK
    private lateinit var networkClient: NetworkClient

    @MockK
    private lateinit var pokemonMapper: PokemonMapper

    @MockK
    private lateinit var getPokemonsResponseApiModel: GetPokemonsResponseApiModel

    @MockK
    private lateinit var pokemonListApiModel: List<PokemonApiModel>

    @InjectMockKs
    private lateinit var networkDataSourceImpl: NetworkDataSourceImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockApiService()
    }

    private fun mockApiService() {
        val retrofit = mockk<Retrofit>()
        every { networkClient.builder } returns retrofit
        every { retrofit.create(ApiService::class.java) } returns apiService
    }

    private fun mockApiServicePokemonListApi(getPokemonsResponseApiModel: GetPokemonsResponseApiModel) {
        coEvery { apiService.getPokemons() } returns getPokemonsResponseApiModel
        every { getPokemonsResponseApiModel.pokemonList } returns pokemonListApiModel
    }

    @Test
    fun `Call ApiService to get pokemons when getPokemons() invoked`() {
        mockApiServicePokemonListApi(getPokemonsResponseApiModel)

        runBlocking { networkDataSourceImpl.getPokemons(,) }

        coVerify(exactly = 1) { apiService.getPokemons() }
    }

    @Test
    fun `Call PokemonMapper to map list of pokemon API response model to simple pokemon model when getPokemons() invoked`() {
        mockApiServicePokemonListApi(getPokemonsResponseApiModel)

        runBlocking { networkDataSourceImpl.getPokemons(,) }

        verify(exactly = 1) { pokemonMapper.map(pokemonListApiModel) }
    }
}
