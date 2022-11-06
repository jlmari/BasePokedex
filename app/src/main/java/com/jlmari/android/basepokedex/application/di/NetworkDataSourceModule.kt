package com.jlmari.android.basepokedex.application.di

import com.jlmari.android.basepokedex.BuildConfig
import com.jlmari.android.basepokedex.application.scopes.PerApplication
import com.jlmari.android.basepokedex.networkdatasource.client.NetworkClient
import dagger.Module
import dagger.Provides

@Module
class NetworkDataSourceModule {

    @Provides
    @PerApplication
    fun provideNetworkClient(): NetworkClient = NetworkClient(BuildConfig.DEBUG)
}
