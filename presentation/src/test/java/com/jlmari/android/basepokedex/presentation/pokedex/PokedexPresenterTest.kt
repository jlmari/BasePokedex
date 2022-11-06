package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonsUseCase
import com.jlmari.android.basepokedex.domain.utils.Failure
import com.jlmari.android.basepokedex.domain.utils.Success
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

internal class PokedexPresenterTest {

    @MockK
    private lateinit var pokedexView: PokedexContract.View

    @MockK
    private lateinit var appDispatchers: AppDispatchers

    @MockK
    private lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @InjectMockKs
    private lateinit var pokedexPresenter: PokedexPresenter

    private val nonEmptyPokemonList: List<PokemonModel> = listOf(
        PokemonModel("bulbasur", 1),
        PokemonModel("charmander", 4),
        PokemonModel("squirtle", 7)
    )

    private val emptyPokemonList: List<PokemonModel> = emptyList()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `Call GetPokemonsUseCase with default values when onCreate()`() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(emptyPokemonList)

        pokedexPresenter.onCreate()

        coVerify(exactly = 1) { getPokemonsUseCase.invoke(0, 50) }
    }

    @Test
    fun `Call GetPokemonsUseCase with provided offset value when onScrollFinished()`() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(emptyPokemonList)

        pokedexPresenter.onScrollFinished()

        coVerify(exactly = 1) { getPokemonsUseCase.invoke(0, 50) }
    }

    @Test
    fun `Call View to show progress while expecting GetPokemonUseCase response and then hide progress and update pokedex when GetPokemonUseCase returns a non-empty list of pokemon`() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(nonEmptyPokemonList)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verifyOrder {
            pokedexView.showProgress()
            pokedexView.hideProgress()
            pokedexView.updatePokedex(nonEmptyPokemonList)
        }
    }

    @Test
    fun `Call View to show progress while expecting GetPokemonUseCase response and then hide progress and show no more pokemons error when GetPokemonUseCase returns an empty list of pokemon`() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(emptyPokemonList)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verifyOrder {
            pokedexView.showProgress()
            pokedexView.hideProgress()
            pokedexView.showNoMorePokemonsError()
        }
    }

    @Test
    fun `Call View to show progress while expecting GetPokemonUseCase response and then hide progress and show error when GetPokemonUseCase returns error`() {
        val genericError = ErrorModel("GenericError")
        every { appDispatchers.main } returns Dispatchers.Unconfined
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Failure(genericError)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verifyOrder {
            pokedexView.showProgress()
            pokedexView.hideProgress()
            pokedexView.showErrorMessage(genericError.errorMessage)
        }
    }
}
