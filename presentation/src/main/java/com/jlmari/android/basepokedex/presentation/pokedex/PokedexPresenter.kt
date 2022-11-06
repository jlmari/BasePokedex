package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonsUseCase
import com.jlmari.android.basepokedex.domain.utils.either
import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokedexPresenter @Inject constructor(
    appDispatchers: AppDispatchers,
    private val getPokemonsUseCase: GetPokemonsUseCase
) : BasePresenter<PokedexContract.View, PokedexContract.Router>(), PokedexContract.Presenter {

    private val errorHandler: CoroutineExceptionHandler by lazy { CoroutineExceptionHandler { _, e -> e.printStackTrace() } }
    private val scope: CoroutineScope by lazy { CoroutineScope(appDispatchers.main + SupervisorJob() + errorHandler) }

    private var pokedexOffset: Int = DEFAULT_OFFSET

    override fun onCreate() {
        super.onCreate()
        requestNewPokemons()
    }

    override fun onScrollFinished() {
        requestNewPokemons(pokedexOffset)
    }

    private fun requestNewPokemons(offset: Int = DEFAULT_OFFSET) {
        scope.launch {
            viewAction { showProgress() }
            val request = getPokemonsUseCase.invoke(offset, DEFAULT_LIMIT)
            request.either(
                onSuccess = {
                    viewAction { hideProgress() }
                    if (it.isNotEmpty()) {
                        pokedexOffset += DEFAULT_LIMIT
                        viewAction { updatePokedex(it) }
                    } else {
                        viewAction { showNoMorePokemonsError() }
                    }
                }, onFailure = {
                    viewAction {
                        hideProgress()
                        showErrorMessage(it.errorMessage)
                    }
                }
            )
        }
    }

    override fun onPokemonItemClicked(pokemonId: Int) {
        routerAction { navigateToPokemonDetail(pokemonId) }
    }

    companion object {
        private const val DEFAULT_OFFSET: Int = 0
        private const val DEFAULT_LIMIT: Int = 50
    }
}
