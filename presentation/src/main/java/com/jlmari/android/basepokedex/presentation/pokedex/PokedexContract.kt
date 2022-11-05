package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.presentation.base.BaseContract

interface PokedexContract {

    interface View : BaseContract.View {

        fun updatePokedex(newPokemons: List<PokemonModel>)

        fun showNoMorePokemonsError()

        fun showErrorMessage(errorMessage: String?)
    }

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router> {

        fun onScrollFinished()
    }
}
