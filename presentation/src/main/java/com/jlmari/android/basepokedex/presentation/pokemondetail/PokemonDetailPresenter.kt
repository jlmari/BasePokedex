package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonDetailUseCase
import com.jlmari.android.basepokedex.domain.utils.either
import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor(
    appDispatchers: AppDispatchers,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : BasePresenter<PokemonDetailContract.View, PokemonDetailContract.Router>(appDispatchers),
    PokemonDetailContract.Presenter {

    private var pokemonId: Int = 0

    override fun onPokemonIdRetrieved(pokemonId: Int) {
        this.pokemonId = pokemonId
    }

    override fun onCreate() {
        super.onCreate()
        requestPokemonInfo()
    }

    override fun onReloadDetailButtonClicked() {
        requestPokemonInfo()
    }

    private fun requestPokemonInfo() {
        scope.launch {
            viewAction { showProgress() }
            getPokemonDetailUseCase.invoke(pokemonId).either(
                onSuccess = {
                    viewAction {
                        hideProgress()
                        hideReloadButton()
                        drawPokemonDetail(it)
                    }
                }, onFailure = {
                    viewAction {
                        hideProgress()
                        showReloadButton()
                    }
                }
            )
        }
    }
}
