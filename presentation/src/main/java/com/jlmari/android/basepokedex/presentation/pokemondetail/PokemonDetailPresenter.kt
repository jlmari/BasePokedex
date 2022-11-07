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

    private var pokemonId: Int = UNASSIGNED_POKEMON_ID

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
        if (pokemonId != UNASSIGNED_POKEMON_ID) {
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
                            showErrorMessage(it.errorMessage)
                        }
                    }
                )
            }
        }
    }

    companion object {
        private const val UNASSIGNED_POKEMON_ID: Int = 0
    }
}
