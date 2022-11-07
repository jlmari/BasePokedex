package com.jlmari.android.basepokedex.memorydatasource

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.memorydatasource.dao.PokemonDetailDao
import com.jlmari.android.basepokedex.memorydatasource.mappers.PokemonDetailInDbMapper
import com.jlmari.android.basepokedex.memorydatasource.mappers.PokemonDetailOutDbMapper
import com.jlmari.android.basepokedex.memorydatasource.models.PokemonDetailDbModel
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class MemoryDataSourceImplTest {

    @MockK
    private lateinit var pokemonDetailDao: PokemonDetailDao

    @MockK
    private lateinit var pokemonDetailOutDbMapper: PokemonDetailOutDbMapper

    @MockK
    private lateinit var pokemonDetailInDbMapper: PokemonDetailInDbMapper

    @MockK
    private lateinit var pokemonDetailModel: PokemonDetailModel

    @MockK
    private lateinit var pokemonDetailDbModel: PokemonDetailDbModel

    @InjectMockKs
    private lateinit var memoryDataSourceImpl: MemoryDataSourceImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    private fun mockGetPokemonDetailDaoAndMapper() {
        coEvery { pokemonDetailDao.getPokemonDetail(any()) } returns pokemonDetailDbModel
        every { pokemonDetailDbModel.storedTime } returns 10
        every { pokemonDetailOutDbMapper.map(any()) } returns pokemonDetailModel
    }

    private fun mockSavePokemonDetailMapper() {
        every { pokemonDetailInDbMapper.map(any()) } returns pokemonDetailDbModel
    }

    @Test
    fun `Call PokemonDetailDao to getPokemonDetail() with correct id when getPokemonDetail() invoked`() {
        val pokemonId = 1
        mockGetPokemonDetailDaoAndMapper()

        runBlocking { memoryDataSourceImpl.getPokemonDetail(pokemonId) }

        coVerify(exactly = 1) { pokemonDetailDao.getPokemonDetail(pokemonId) }
    }

    @Test
    fun `Call PokemonDetailDbMapper to map pokemon detail response model to simple pokemon detail model when getPokemonDetail() invoked`() {
        mockGetPokemonDetailDaoAndMapper()

        runBlocking { memoryDataSourceImpl.getPokemonDetail(2) }

        verify(exactly = 1) { pokemonDetailOutDbMapper.map(pokemonDetailDbModel) }
    }

    @Test
    fun `Call PokemonDetailDao to insertPokemonDetail() with correct pokemon detail when savePokemonDetail() invoked`() {
        mockSavePokemonDetailMapper()

        runBlocking { memoryDataSourceImpl.savePokemonDetail(pokemonDetailModel) }

        coVerify(exactly = 1) { pokemonDetailDao.insertPokemonDetail(pokemonDetailDbModel) }
    }
}
