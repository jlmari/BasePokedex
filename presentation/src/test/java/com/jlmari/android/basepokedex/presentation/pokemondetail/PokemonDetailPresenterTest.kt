package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonDetailUseCase
import com.jlmari.android.basepokedex.domain.utils.Success
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

internal class PokemonDetailPresenterTest {

    @MockK
    private lateinit var pokemonDetailView: PokemonDetailContract.View

    @MockK
    private lateinit var appDispatchers: AppDispatchers

    @MockK
    private lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase

    @InjectMockKs
    private lateinit var pokemonDetailPresenter: PokemonDetailPresenter

    private val examplePokemonDetail: PokemonDetailModel = PokemonDetailModel(
        1,
        1,
        "bulbasaur",
        9.0,
        0.7,
        listOf("grass, poison"),
        "https://photoexampleback.png",
        "https://photoexamplefront.png",
        listOf("overgrow"),
        listOf("cut", "bind", "vine", "headbutt", "tackle", "body-slam")
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `Call GetPokemonDetailUseCase with correct pokemon id when onCreate()`() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(examplePokemonDetail)

        val retrievedId = 1
        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()

        coVerify(exactly = 1) { getPokemonDetailUseCase.invoke(retrievedId) }
    }
}
