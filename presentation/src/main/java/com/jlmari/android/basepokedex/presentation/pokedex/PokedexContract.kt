package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.presentation.base.BaseContract

interface PokedexContract {

    interface View : BaseContract.View

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}
