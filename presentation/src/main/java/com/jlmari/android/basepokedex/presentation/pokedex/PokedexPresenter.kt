package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonsUseCase
import com.jlmari.android.basepokedex.domain.utils.either
import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokedexPresenter @Inject constructor(
    appDispatchers: AppDispatchers,
    private val getPokemonsUseCase: GetPokemonsUseCase
) : BasePresenter<PokedexContract.View, PokedexContract.Router>(appDispatchers), PokedexContract.Presenter {

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
            getPokemonsUseCase.invoke(offset, DEFAULT_LIMIT).either(
                onSuccess = { pokemonList ->
                    viewAction { hideProgress() }
                    if (pokemonList.isNotEmpty()) {
                        pokedexOffset += DEFAULT_LIMIT
                        viewAction { updatePokedex(pokemonList) }
                    } else {
                        viewAction { showNoMorePokemonsError() }
                    }
                }, onFailure = { error ->
                    viewAction {
                        hideProgress()
                        showErrorMessage(error.errorMessage)
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
