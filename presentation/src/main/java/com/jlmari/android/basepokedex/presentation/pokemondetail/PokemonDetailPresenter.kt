package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonDetailUseCase
import com.jlmari.android.basepokedex.domain.utils.either
import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(
    appDispatchers: AppDispatchers,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : BasePresenter<PokemonDetailContract.View, PokemonDetailContract.Router>(),
    PokemonDetailContract.Presenter {

    private val errorHandler: CoroutineExceptionHandler by lazy { CoroutineExceptionHandler { _, e -> e.printStackTrace() } }
    private val scope: CoroutineScope by lazy { CoroutineScope(appDispatchers.main + SupervisorJob() + errorHandler) }

    private var pokemonId: Int = 0

    override fun onPokemonIdRetrieved(pokemonId: Int) {
        this.pokemonId = pokemonId
    }

    override fun onCreate() {
        super.onCreate()
        requestPokemonInfo()
    }

    private fun requestPokemonInfo() {
        scope.launch {
            viewAction { showProgress() }
            getPokemonDetailUseCase.invoke(pokemonId).either(
                onSuccess = {
                    viewAction { hideProgress() }
                }, onFailure = {
                    viewAction { hideProgress() }
                }
            )
        }
    }
}
