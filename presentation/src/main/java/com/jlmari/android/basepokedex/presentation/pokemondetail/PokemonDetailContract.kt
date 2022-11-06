package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.presentation.base.BaseContract

interface PokemonDetailContract {

    interface View : BaseContract.View {

        fun showReloadButton()

        fun hideReloadButton()

        fun drawPokemonDetail(pokemonDetail: PokemonDetailModel)
    }

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router> {

        fun onPokemonIdRetrieved(pokemonId: Int)

        fun onReloadDetailButtonClicked()
    }
}
