package com.jlmari.android.basepokedex.memorydatasource.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.jlmari.android.basepokedex.memorydatasource.database.AppDataBase
import com.jlmari.android.basepokedex.memorydatasource.models.PokemonDetailDbModel
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
internal class PokemonDetailDaoTest {

    private lateinit var appDataBase: AppDataBase
    private lateinit var pokemonDetailDao: PokemonDetailDao

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        appDataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        pokemonDetailDao = appDataBase.pokemonDetailDao()
    }

    @After
    fun tearDown() {
        appDataBase.close()
    }

    private fun createTestPokemonDetailDb(id: Int): PokemonDetailDbModel =
        PokemonDetailDbModel(
            id,
            1,
            "bulbasaur",
            9.0,
            0.7,
            listOf("grass, poison"),
            "https://photoexampleback.png",
            "https://photoexamplefront.png",
            listOf("overgrow"),
            listOf("cut", "bind", "vine", "headbutt", "tackle", "body-slam"),
            Date().time
        )

    @Test
    fun `Check that inserted PokemonDetailDbModel and then get PokemonDetailDbModel from its id return the same Pokemon`() {
        val pokemonDetailDb = createTestPokemonDetailDb(12)

        val result = runBlocking {
            pokemonDetailDao.insertPokemonDetail(pokemonDetailDb)
            pokemonDetailDao.getPokemonDetail(pokemonDetailDb.id)
        }

        assertEquals(pokemonDetailDb.id, result?.id)
        assertEquals(pokemonDetailDb.order, result?.order)
        assertEquals(pokemonDetailDb.name, result?.name)
        assertEquals(pokemonDetailDb.weightKg, result?.weightKg)
        assertEquals(pokemonDetailDb.heightMeters, result?.heightMeters)
    }

    @Test
    fun `Check that after deleting PokemonDetailDbModel from its id and then get the returned value of following getters is null`() {
        val pokemonDetailDb = createTestPokemonDetailDb(45)

        val result = runBlocking {
            pokemonDetailDao.insertPokemonDetail(pokemonDetailDb)
            pokemonDetailDao.deletePokemonDetail(pokemonDetailDb.id)
            pokemonDetailDao.getPokemonDetail(pokemonDetailDb.id)
        }

        assertNull(result)
    }

    @Test
    fun `Check that multiple insertions of PokemonDetailDbModel and then get the returned pokemon detail list are the same size`() {
        val pokemonDetail1 = createTestPokemonDetailDb(1)
        val pokemonDetail2 = createTestPokemonDetailDb(2)
        val pokemonDetail3 = createTestPokemonDetailDb(3)
        val pokemonDetailList = listOf(pokemonDetail1, pokemonDetail2, pokemonDetail3)

        runBlocking {
            pokemonDetailList.forEach { pokemonDetail ->
                pokemonDetailDao.insertPokemonDetail(pokemonDetail)
            }
        }

        var storedPokemonDetailSize = 0
        runBlocking {
            pokemonDetailList.forEach { pokemonDetail ->
                if (pokemonDetailDao.getPokemonDetail(pokemonDetail.id) != null) {
                    storedPokemonDetailSize++
                }
            }
        }

        assertEquals(3, storedPokemonDetailSize)
    }
}
