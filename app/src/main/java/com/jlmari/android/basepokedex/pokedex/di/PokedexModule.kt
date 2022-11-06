package com.jlmari.android.basepokedex.pokedex.di

import com.jlmari.android.basepokedex.application.scopes.PerFragment
import com.jlmari.android.basepokedex.presentation.pokedex.PokedexContract
import com.jlmari.android.basepokedex.presentation.pokedex.PokedexPresenter
import dagger.Binds
import dagger.Module

@Module
interface PokedexModule {

    @Binds
    @PerFragment
    fun bindsPresenter(presenter: PokedexPresenter): PokedexContract.Presenter
}
