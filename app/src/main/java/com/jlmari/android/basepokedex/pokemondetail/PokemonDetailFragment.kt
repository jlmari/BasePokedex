package com.jlmari.android.basepokedex.pokemondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.BaseFragment
import com.jlmari.android.basepokedex.databinding.FrPokemonDetailBinding
import com.jlmari.android.basepokedex.presentation.pokemondetail.PokemonDetailContract

class PokemonDetailFragment :
    BaseFragment<PokemonDetailContract.View, PokemonDetailContract.Router, PokemonDetailContract.Presenter, FrPokemonDetailBinding>(),
    PokemonDetailContract.View, PokemonDetailContract.Router {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FrPokemonDetailBinding
        get() = FrPokemonDetailBinding::inflate

    private val navArgs: PokemonDetailFragmentArgs by navArgs()

    override fun injectDependencies(appComponent: AppComponent?) {
        appComponent?.pokemonDetailFactory()
            ?.create()
            ?.inject(this)
    }

    override fun retrieveBundleData() {
        super.retrieveBundleData()
        presenter.onPokemonIdRetrieved(navArgs.pokemonId)
    }
}
