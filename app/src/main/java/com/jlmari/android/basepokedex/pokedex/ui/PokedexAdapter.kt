package com.jlmari.android.basepokedex.pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmari.android.basepokedex.databinding.ItemPokemonListBinding
import com.jlmari.android.basepokedex.domain.models.PokemonModel
import java.util.*

class PokedexAdapter(private val items: MutableList<PokemonModel>) :
    RecyclerView.Adapter<PokedexAdapter.ViewHolder>() {

    private lateinit var binding: ItemPokemonListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemPokemonListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokedexAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: ItemPokemonListBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        fun bind(item: PokemonModel) {
            binding.tvPokemonName.text = item.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        }
    }
}
