package com.jlmari.android.basepokedex.pokemondetail.di

import com.jlmari.android.basepokedex.application.scopes.PerFragment
import com.jlmari.android.basepokedex.presentation.pokemondetail.PokemonDetailContract
import com.jlmari.android.basepokedex.presentation.pokemondetail.PokemonDetailPresenter
import dagger.Binds
import dagger.Module

@Module
interface PokemonDetailModule {

    @Binds
    @PerFragment
    fun bindsPresenter(presenter: PokemonDetailPresenter): PokemonDetailContract.Presenter
}
