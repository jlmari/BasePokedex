package com.jlmari.android.basepokedex.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jlmari.android.basepokedex.R
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.BaseFragment
import com.jlmari.android.basepokedex.databinding.FrPokedexBinding
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import com.jlmari.android.basepokedex.pokedex.ui.PokedexAdapter
import com.jlmari.android.basepokedex.presentation.pokedex.PokedexContract
import com.jlmari.android.basepokedex.utils.showToast

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
                // Check when users scroll to bottom and notify presenter
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    presenter.onScrollFinished()
                }
            })
        }
    }

    override fun showProgress() {
        getBinding().pbLoadMorePokemons.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        getBinding().pbLoadMorePokemons.visibility = View.GONE
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

    override fun showNoMorePokemonsError() {
        context?.showToast(getString(R.string.no_more_pokemons_error))
    }

    override fun showErrorMessage(errorMessage: String?) {
        context?.showToast(errorMessage ?: getString(R.string.generic_error))
    }
}
