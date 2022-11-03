package com.jlmari.android.basepokedex.main.di

import com.jlmari.android.basepokedex.application.scopes.PerActivity
import com.jlmari.android.basepokedex.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}
