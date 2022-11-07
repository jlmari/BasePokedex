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
    private lateinit var pokedexRouter: PokedexContract.Router

    @MockK
    private lateinit var appDispatchers: AppDispatchers

    @MockK
    private lateinit var getPokemonsUseCase: GetPokemonsUseCase

    @MockK
    private lateinit var mockPokemonList: List<PokemonModel>

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

    private fun mockMainDispatcher() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
    }

    @Test
    fun `Call GetPokemonsUseCase with default values when onCreate()`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(mockPokemonList)

        pokedexPresenter.onCreate()

        coVerify(exactly = 1) { getPokemonsUseCase.invoke(0, 50) }
    }

    @Test
    fun `Call GetPokemonsUseCase with provided offset value when onScrollFinished()`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(mockPokemonList)

        pokedexPresenter.onScrollFinished()

        coVerify(exactly = 1) { getPokemonsUseCase.invoke(0, 50) }
    }

    @Test
    fun `Call View (if attached) to show progress while expecting GetPokemonsUseCase response and then hide progress when GetPokemonsUseCase returns success response`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(mockPokemonList)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verifyOrder {
            pokedexView.showProgress()
            pokedexView.hideProgress()
        }
    }

    @Test
    fun `Call View (if attached) to update pokedex when GetPokemonsUseCase returns a non-empty list of pokemon`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(nonEmptyPokemonList)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verify(exactly = 1) { pokedexView.updatePokedex(nonEmptyPokemonList) }
    }

    @Test
    fun `Call View (if attached) to show no more pokemons error when GetPokemonsUseCase returns an empty list of pokemon`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(emptyPokemonList)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verify(exactly = 1) { pokedexView.showNoMorePokemonsError() }
    }

    @Test
    fun `Call View (if attached) to show progress while expecting GetPokemonsUseCase response and then hide progress when GetPokemonsUseCase returns error`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Failure(ErrorModel())

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verifyOrder {
            pokedexView.showProgress()
            pokedexView.hideProgress()
        }
    }

    @Test
    fun `Call View (if attached) to show generic error when GetPokemonsUseCase returns error`() {
        val genericError = ErrorModel("GenericError")
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Failure(genericError)

        pokedexPresenter.attachView(pokedexView)
        pokedexPresenter.onCreate()

        verify(exactly = 1) { pokedexView.showErrorMessage(genericError.errorMessage) }
    }

    @Test
    fun `Do not call View (if not attached) to do show or hide progress when onCreate()`() {
        mockMainDispatcher()
        coEvery { getPokemonsUseCase.invoke(any(), any()) } returns Success(mockPokemonList)

        pokedexPresenter.onCreate()

        verify(exactly = 0) { pokedexView.showProgress() }
        verify(exactly = 0) { pokedexView.hideProgress() }
    }

    @Test
    fun `Call Router (if attached) to navigate to pokemon detail when onPokemonItemClicked()`() {
        val pokemonId = 42

        pokedexPresenter.attachRouter(pokedexRouter)
        pokedexPresenter.onPokemonItemClicked(pokemonId)

        verify(exactly = 1) { pokedexRouter.navigateToPokemonDetail(pokemonId) }
    }

    @Test
    fun `Do not call Router (if not attached) to navigate to pokemon detail when onPokemonItemClicked()`() {
        val pokemonId = 42

        pokedexPresenter.onPokemonItemClicked(pokemonId)

        verify(exactly = 0) { pokedexRouter.navigateToPokemonDetail(pokemonId) }
    }
}
