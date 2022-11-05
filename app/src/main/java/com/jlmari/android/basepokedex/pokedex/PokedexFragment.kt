package com.jlmari.android.basepokedex.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.BaseFragment
import com.jlmari.android.basepokedex.databinding.FrPokedexBinding
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.pokedex.ui.PokedexAdapter
import com.jlmari.android.basepokedex.presentation.pokedex.PokedexContract


class PokedexFragment :
    BaseFragment<PokedexContract.View, PokedexContract.Router, PokedexContract.Presenter, FrPokedexBinding>(),
    PokedexContract.View, PokedexContract.Router {

    private lateinit var pokedexAdapter: PokedexAdapter
    private val pokemonList: MutableList<PokemonModel> = mutableListOf()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FrPokedexBinding
        get() = FrPokedexBinding::inflate

    override fun injectDependencies(appComponent: AppComponent?) {
        appComponent?.pokedexComponentFactory()
            ?.create()
            ?.inject(this)
    }

    override fun setupListeners() {
        super.setupListeners()
        withBinding {
            svPokedex.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                // Check when users scroll to bottom
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    presenter.onScrollFinished()
                }
            })
        }
    }

    override fun updatePokedex(newPokemons: List<PokemonModel>) {
        pokemonList.addAll(newPokemons)
        pokedexAdapter = PokedexAdapter(pokemonList)
        withBinding {
            rvPokedex.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = pokedexAdapter
            }
        }
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
