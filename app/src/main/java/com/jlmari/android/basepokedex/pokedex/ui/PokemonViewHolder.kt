package com.jlmari.android.basepokedex.pokedex.ui

import androidx.recyclerview.widget.RecyclerView
import com.jlmari.android.basepokedex.databinding.ItemPokemonListBinding
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import java.util.*

class PokemonViewHolder(
    private val binding: ItemPokemonListBinding,
    private val onItemClickListener: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: PokemonModel) {
        with(binding) {
            cvPokemonItem.setOnClickListener { onItemClickListener.invoke(pokemon.id) }
            tvPokemonName.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        }
    }
}
