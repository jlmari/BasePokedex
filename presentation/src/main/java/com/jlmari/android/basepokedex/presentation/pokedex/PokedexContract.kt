package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.presentation.base.BaseContract

interface PokedexContract {

    interface View : BaseContract.View {

        fun showMessage(message: String)

        fun showError(errorMessage: String)
    }

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}
