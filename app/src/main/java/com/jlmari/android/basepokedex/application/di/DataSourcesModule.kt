package com.jlmari.android.basepokedex.application.di

import com.jlmari.android.basepokedex.application.scopes.PerApplication
import com.jlmari.android.basepokedex.data.datasources.NetworkDataSource
import com.jlmari.android.basepokedex.networkdatasource.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourcesModule {

    @Binds
    @PerApplication
    abstract fun providesNetworkDataSource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource
}
