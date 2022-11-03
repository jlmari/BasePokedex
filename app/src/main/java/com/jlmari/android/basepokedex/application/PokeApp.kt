package com.jlmari.android.basepokedex.application

import android.app.Application
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.application.di.DaggerAppComponent

class PokeApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
