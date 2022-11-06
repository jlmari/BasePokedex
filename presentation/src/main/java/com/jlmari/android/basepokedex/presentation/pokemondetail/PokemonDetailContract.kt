package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.presentation.base.BaseContract

interface PokemonDetailContract {

    interface View : BaseContract.View

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}
