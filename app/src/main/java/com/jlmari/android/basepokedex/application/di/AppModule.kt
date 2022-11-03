package com.jlmari.android.basepokedex.application.di

import android.content.Context
import com.jlmari.android.basepokedex.application.PokeApp
import com.jlmari.android.basepokedex.application.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: PokeApp) {

    @Provides
    @PerApplication
    internal fun provideContext(): Context = app
}
