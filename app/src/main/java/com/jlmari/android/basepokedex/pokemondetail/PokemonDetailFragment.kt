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

    override fun drawPokemonDetail(pokemonDetail: PokemonDetailModel) {
        withBinding {
            ivPokemonBackPhoto.loadImage(pokemonDetail.backPhotoUrl)
            ivPokemonFrontPhoto.loadImage(pokemonDetail.frontPhotoUrl)
            tvPokemonOrder.text = getString(R.string.pokemon_detail_order, pokemonDetail.order)
            tvPokemonName.text = getString(R.string.pokemon_detail_name, pokemonDetail.name)
            tvPokemonWeight.text = getString(R.string.pokemon_detail_weight, pokemonDetail.weightKg)
            tvPokemonHeight.text = getString(R.string.pokemon_detail_height, pokemonDetail.heightMeters)
            context?.let { context ->
                spPokemonTypes.adapter =
                    pokemonDetail.types.createSimpleArrayAdapter(context).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                spPokemonAbilities.adapter =
                    pokemonDetail.abilities.createSimpleArrayAdapter(context).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                spPokemonMoves.adapter =
                    pokemonDetail.moves.createSimpleArrayAdapter(context).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
            }
        }
    }

    private fun List<Any>.createSimpleArrayAdapter(context: Context): ArrayAdapter<Any> =
        ArrayAdapter(context, android.R.layout.simple_spinner_item, this)

    override fun showErrorMessage(errorMessage: String?) {
        context?.showToast(errorMessage ?: getString(R.string.generic_error))
    }
}
