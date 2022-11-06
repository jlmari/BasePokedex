package com.jlmari.android.basepokedex.main.di

import com.jlmari.android.basepokedex.application.scopes.PerActivity
import com.jlmari.android.basepokedex.presentation.main.MainContract
import com.jlmari.android.basepokedex.presentation.main.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @Binds
    @PerActivity
    internal abstract fun bindsPresenter(mainPresenter: MainPresenter): MainContract.Presenter
}
