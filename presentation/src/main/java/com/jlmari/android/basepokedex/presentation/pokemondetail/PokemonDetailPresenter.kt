package com.jlmari.android.basepokedex.presentation.pokemondetail

import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import javax.inject.Inject

class PokemonDetailPresenter @Inject constructor() :
    BasePresenter<PokemonDetailContract.View, PokemonDetailContract.Router>(),
    PokemonDetailContract.Presenter
