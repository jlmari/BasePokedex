package com.jlmari.android.basepokedex.pokedex

import android.widget.Toast
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

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
