package com.jlmari.android.basepokedex.main

import com.jlmari.android.basepokedex.R
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.BaseActivity
import com.jlmari.android.basepokedex.presentation.main.MainContract

class MainActivity : BaseActivity<MainContract.View, MainContract.Router, MainContract.Presenter>(),
    MainContract.View, MainContract.Router {

    override val layoutResId = R.layout.ac_main

    override fun injectDependencies(appComponent: AppComponent?) {
        appComponent?.mainComponentFactory()
            ?.create()
            ?.inject(this)
    }
}
