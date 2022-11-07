package com.jlmari.android.basepokedex.pokemondetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import com.jlmari.android.basepokedex.R
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.BaseFragment
import com.jlmari.android.basepokedex.databinding.FrPokemonDetailBinding
import com.jlmari.android.basepokedex.domain.models.PokemonDetailModel
import com.jlmari.android.basepokedex.presentation.pokemondetail.PokemonDetailContract
import com.jlmari.android.basepokedex.utils.loadImage
import com.jlmari.android.basepokedex.utils.showToast
import java.util.*

class PokemonDetailFragment :
    BaseFragment<PokemonDetailContract.View, PokemonDetailContract.Router, PokemonDetailContract.Presenter, FrPokemonDetailBinding>(),
    PokemonDetailContract.View, PokemonDetailContract.Router {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FrPokemonDetailBinding
        get() = FrPokemonDetailBinding::inflate

    private val navArgs: PokemonDetailFragmentArgs by navArgs()

    override fun injectDependencies(appComponent: AppComponent?) {
        appComponent?.pokemonDetailFactory()?.create()?.inject(this)
    }

    override fun retrieveBundleData() {
        super.retrieveBundleData()
        presenter.onPokemonIdRetrieved(navArgs.pokemonId)
    }

    override fun setupListeners() {
        super.setupListeners()
        getBinding().btnReloadDetail.setOnClickListener { presenter.onReloadDetailButtonClicked() }
    }

    override fun showReloadButton() {
        getBinding().btnReloadDetail.visibility = View.VISIBLE
    }

    override fun hideReloadButton() {
        getBinding().btnReloadDetail.visibility = View.GONE
    }

    override fun drawPokemonDetail(pokemon: PokemonDetailModel) {
        withBinding {
            ivPokemonBackPhoto.loadImage(pokemon.backPhotoUrl)
            ivPokemonFrontPhoto.loadImage(pokemon.frontPhotoUrl)
            tvPokemonOrder.text = getString(R.string.pokemon_detail_order, pokemon.order)
            tvPokemonName.text = getString(R.string.pokemon_detail_name,
                pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
            tvPokemonWeight.text = getString(R.string.pokemon_detail_weight, pokemon.weightKg)
            tvPokemonHeight.text = getString(R.string.pokemon_detail_height, pokemon.heightMeters)
            context?.let { context ->
                spPokemonTypes.adapter = pokemon.types.createSimpleArrayAdapter(
                    context,
                    getString(R.string.pokemon_detail_types)
                ).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
                spPokemonAbilities.adapter = pokemon.abilities.createSimpleArrayAdapter(
                    context,
                    getString(R.string.pokemon_detail_abilities)
                ).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
                spPokemonMoves.adapter = pokemon.moves.createSimpleArrayAdapter(
                    context,
                    getString(R.string.pokemon_detail_moves)
                ).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
            }
        }
    }

    private fun List<String>.createSimpleArrayAdapter(
        context: Context,
        label: String
    ): ArrayAdapter<String> {
        val adapterList = this.toMutableList()
            .apply { add(0, getString(R.string.pokemon_detail_spinner_first_item, label)) }
        return ArrayAdapter(context, android.R.layout.simple_spinner_item, adapterList)
    }

    override fun showErrorMessage(errorMessage: String?) {
        context?.showToast(errorMessage ?: getString(R.string.generic_error))
    }
}
