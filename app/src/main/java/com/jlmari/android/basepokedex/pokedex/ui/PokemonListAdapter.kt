package com.jlmari.android.basepokedex.pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlmari.android.basepokedex.databinding.ItemPokemonListBinding
import com.jlmari.android.basepokedex.domain.models.PokemonModel

class PokemonListAdapter : RecyclerView.Adapter<PokemonViewHolder>() {

    private lateinit var binding: ItemPokemonListBinding

    private lateinit var items: MutableList<PokemonModel>
    private lateinit var onItemClickListener: (Int) -> Unit

    fun setupAdapter(items: MutableList<PokemonModel>, onItemClickListener: (Int) -> Unit) {
        this.items = items
        this.onItemClickListener = onItemClickListener
    }

    fun addItems(newItems: List<PokemonModel>) {
        val positionStart = items.size
        val itemCountInserted = newItems.size
        items.addAll(newItems)
        notifyItemRangeInserted(positionStart, itemCountInserted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemPokemonListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
