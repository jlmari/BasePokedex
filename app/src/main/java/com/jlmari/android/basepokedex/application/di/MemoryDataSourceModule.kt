package com.jlmari.android.basepokedex.application.di

import android.content.Context
import com.jlmari.android.basepokedex.application.scopes.PerApplication
import com.jlmari.android.basepokedex.memorydatasource.dao.PokemonDetailDao
import com.jlmari.android.basepokedex.memorydatasource.database.AppDataBase
import dagger.Module
import dagger.Provides

@Module
object MemoryDataSourceModule {

    @Provides
    @PerApplication
    fun provideDb(appContext: Context): AppDataBase =
        AppDataBase.getInstance(appContext)

    @Provides
    @PerApplication
    fun providePokemonDetailDao(appDataBase: AppDataBase): PokemonDetailDao =
        appDataBase.pokemonDetailDao()
}
