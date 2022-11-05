package com.jlmari.android.basepokedex.pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmari.android.basepokedex.databinding.ItemPokemonListBinding
import com.jlmari.android.basepokedex.domain.models.PokemonModel

class PokemonListAdapter(private val items: MutableList<PokemonModel>) :
    RecyclerView.Adapter<PokemonViewHolder>() {

    private lateinit var binding: ItemPokemonListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemPokemonListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
