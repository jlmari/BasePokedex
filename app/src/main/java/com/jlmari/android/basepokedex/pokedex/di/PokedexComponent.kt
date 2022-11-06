package com.jlmari.android.basepokedex.pokedex.di

import com.jlmari.android.basepokedex.application.scopes.PerFragment
import com.jlmari.android.basepokedex.pokedex.PokedexFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [PokedexModule::class])
interface PokedexComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PokedexComponent
    }

    fun inject(fragment: PokedexFragment)
}
