package com.jlmari.android.basepokedex.networkdatasource

import com.jlmari.android.basepokedex.networkdatasource.client.NetworkClient
import com.jlmari.android.basepokedex.networkdatasource.mappers.PokemonDetailMapper
import com.jlmari.android.basepokedex.networkdatasource.mappers.PokemonMapper
import com.jlmari.android.basepokedex.networkdatasource.models.GetPokemonDetailResponseApiModel
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

    @MockK
    private lateinit var pokemonDetailMapper: PokemonDetailMapper

    @MockK
    private lateinit var getPokemonDetailResponseApiModel: GetPokemonDetailResponseApiModel

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
        coEvery { apiService.getPokemons(any(), any()) } returns getPokemonsResponseApiModel
        every { getPokemonsResponseApiModel.pokemonList } returns pokemonListApiModel
    }

    private fun mockApiServicePokemonDetailApi(getPokemonDetailResponseApiModel: GetPokemonDetailResponseApiModel) {
        coEvery { apiService.getPokemonDetail(any()) } returns getPokemonDetailResponseApiModel
    }

    @Test
    fun `Call ApiService to get pokemons with correct parameters when getPokemons() invoked`() {
        mockApiServicePokemonListApi(getPokemonsResponseApiModel)

        val inputOffset = 0
        val inputLimit = 40
        runBlocking { networkDataSourceImpl.getPokemons(inputOffset, inputLimit) }

        coVerify(exactly = 1) { apiService.getPokemons(inputOffset, inputLimit) }
    }

    @Test
    fun `Call PokemonMapper to map list of pokemon API response model to simple pokemon model when getPokemons() invoked`() {
        mockApiServicePokemonListApi(getPokemonsResponseApiModel)

        runBlocking { networkDataSourceImpl.getPokemons(0, 0) }

        verify(exactly = 1) { pokemonMapper.map(pokemonListApiModel) }
    }

    @Test
    fun `Call ApiService to get pokemon detail with correct id when getPokemonDetail() invoked`() {
        mockApiServicePokemonDetailApi(getPokemonDetailResponseApiModel)

        val pokemonId = 12
        runBlocking { networkDataSourceImpl.getPokemonDetail(pokemonId) }

        coVerify(exactly = 1) { apiService.getPokemonDetail(pokemonId) }
    }

    @Test
    fun `Call PokemonDetailMapper to map details of pokemon API response model to simple pokemon detail model when getPokemonDetail() invoked`() {
        mockApiServicePokemonDetailApi(getPokemonDetailResponseApiModel)

        runBlocking { networkDataSourceImpl.getPokemonDetail(1) }

        verify(exactly = 1) { pokemonDetailMapper.map(getPokemonDetailResponseApiModel) }
    }
}
