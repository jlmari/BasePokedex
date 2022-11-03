package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import javax.inject.Inject

class PokedexPresenter @Inject constructor() :
    BasePresenter<PokedexContract.View, PokedexContract.Router>(),
    PokedexContract.Presenter
