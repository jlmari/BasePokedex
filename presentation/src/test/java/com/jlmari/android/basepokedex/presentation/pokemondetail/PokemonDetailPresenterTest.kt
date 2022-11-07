package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonDetailUseCase
import com.jlmari.android.basepokedex.domain.utils.Failure
import com.jlmari.android.basepokedex.domain.utils.Success
import io.mockk.*
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

    private val testPokemonDetail: PokemonDetailModel = PokemonDetailModel(
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

    private fun mockMainDispatcher() {
        every { appDispatchers.main } returns Dispatchers.Unconfined
    }

    @Test
    fun `Call GetPokemonDetailUseCase with correct pokemon id (if retrieved) when onCreate()`() {
        val retrievedId = 1
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()

        coVerify(exactly = 1) { getPokemonDetailUseCase.invoke(retrievedId) }
    }

    @Test
    fun `Do not call GetPokemonDetailUseCase if pokemon id not retrieved when onCreate()`() {
        val retrievedId = 0
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.onCreate()

        coVerify(exactly = 0) { getPokemonDetailUseCase.invoke(retrievedId) }
    }

    @Test
    fun `Call GetPokemonDetailUseCase with correct pokemon id (if retrieved) when onReloadDetailButtonClicked()`() {
        val retrievedId = 1
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onReloadDetailButtonClicked()

        coVerify(exactly = 1) { getPokemonDetailUseCase.invoke(retrievedId) }
    }

    @Test
    fun `Do not call GetPokemonDetailUseCase if pokemon id not retrieved when onReloadDetailButtonClicked()`() {
        val retrievedId = 0
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.onReloadDetailButtonClicked()

        coVerify(exactly = 0) { getPokemonDetailUseCase.invoke(retrievedId) }
    }

    @Test
    fun `Call View (if attached) to show progress while expecting GetPokemonDetailUseCase response and then hide progress when GetPokemonDetailUseCase returns success response`() {
        val retrievedId = 1
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.attachView(pokemonDetailView)
        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()

        verifyOrder {
            pokemonDetailView.showProgress()
            pokemonDetailView.hideProgress()
        }
    }

    @Test
    fun `Call View (if attached) to hide reload button and draw pokemon detail when GetPokemonDetailUseCase returns success response`() {
        val retrievedId = 1
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.attachView(pokemonDetailView)
        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()

        verify(exactly = 1) { pokemonDetailView.hideReloadButton() }
        verify(exactly = 1) { pokemonDetailView.drawPokemonDetail(testPokemonDetail) }
    }

    @Test
    fun `Do not call View (if not attached) to show progress while expecting GetPokemonDetailUseCase response and then hide progress when GetPokemonDetailUseCase returns success response`() {
        val retrievedId = 1
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Success(testPokemonDetail)

        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()


        verify(exactly = 0) { pokemonDetailView.showProgress() }
        verify(exactly = 0) { pokemonDetailView.hideProgress() }
    }

    @Test
    fun `Call View (if attached) to show progress while expecting GetPokemonDetailUseCase response and then hide progress when GetPokemonDetailUseCase returns error`() {
        val retrievedId = 2
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Failure(ErrorModel())

        pokemonDetailPresenter.attachView(pokemonDetailView)
        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()

        verifyOrder {
            pokemonDetailView.showProgress()
            pokemonDetailView.hideProgress()
        }
    }

    @Test
    fun `Call View (if attached) to show reload button and show generic error when GetPokemonDetailUseCase returns error`() {
        val retrievedId = 2
        val genericError = ErrorModel("GenericError")
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Failure(genericError)

        pokemonDetailPresenter.attachView(pokemonDetailView)
        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()

        verifyOrder {
            pokemonDetailView.showReloadButton()
            pokemonDetailView.showErrorMessage(genericError.errorMessage)
        }
    }

    @Test
    fun `Do not call View (if not attached) to show progress while expecting GetPokemonDetailUseCase response and then hide progress when GetPokemonDetailUseCase returns error`() {
        val retrievedId = 2
        mockMainDispatcher()
        coEvery { getPokemonDetailUseCase.invoke(any()) } returns Failure(ErrorModel())

        pokemonDetailPresenter.onPokemonIdRetrieved(retrievedId)
        pokemonDetailPresenter.onCreate()


        verify(exactly = 0) { pokemonDetailView.showProgress() }
        verify(exactly = 0) { pokemonDetailView.hideProgress() }
    }
}
