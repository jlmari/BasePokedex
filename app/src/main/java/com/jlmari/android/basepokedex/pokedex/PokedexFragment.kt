package com.jlmari.android.basepokedex.pokedex

import com.jlmari.android.basepokedex.R
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.BaseFragment
import com.jlmari.android.basepokedex.presentation.pokedex.PokedexContract

class PokedexFragment(override val layoutResId: Int = R.layout.fr_pokedex) :
    BaseFragment<PokedexContract.View, PokedexContract.Router, PokedexContract.Presenter>(),
    PokedexContract.View, PokedexContract.Router {

    override fun injectDependencies(appComponent: AppComponent?) {
        appComponent?.pokedexComponentFactory()
            ?.create()
            ?.inject(this)
    }
}
