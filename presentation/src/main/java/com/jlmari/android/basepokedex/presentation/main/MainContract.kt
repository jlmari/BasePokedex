package com.jlmari.android.basepokedex.presentation.main

import com.jlmari.android.basepokedex.presentation.base.BaseContract

interface MainContract {

    interface View : BaseContract.View

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}
