package com.jlmari.android.basepokedex.presentation.main

import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View, MainContract.Router>(),
    MainContract.Presenter
