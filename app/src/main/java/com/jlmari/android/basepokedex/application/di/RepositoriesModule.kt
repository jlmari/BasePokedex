package com.jlmari.android.basepokedex.application.di

import com.jlmari.android.basepokedex.application.scopes.PerApplication
import com.jlmari.android.basepokedex.data.repositories.PokeRepositoryImpl
import com.jlmari.android.basepokedex.domain.repositories.PokeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    @PerApplication
    abstract fun provideRepository(repository: PokeRepositoryImpl): PokeRepository
}
