package com.jlmari.android.basepokedex.pokemondetail.di

import com.jlmari.android.basepokedex.application.scopes.PerFragment
import com.jlmari.android.basepokedex.pokemondetail.PokemonDetailFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [PokemonDetailModule::class])
interface PokemonDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PokemonDetailComponent
    }

    fun inject(fragment: PokemonDetailFragment)
}
