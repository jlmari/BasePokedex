package com.jlmari.android.basepokedex.presentation.pokedex

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import com.jlmari.android.basepokedex.domain.usecases.GetPokemonsUseCase
import com.jlmari.android.basepokedex.domain.utils.either
import com.jlmari.android.basepokedex.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokedexPresenter @Inject constructor(
    appDispatchers: AppDispatchers,
    private val getPokemonsUseCase: GetPokemonsUseCase
) : BasePresenter<PokedexContract.View, PokedexContract.Router>(), PokedexContract.Presenter {

    private val errorHandler: CoroutineExceptionHandler by lazy { CoroutineExceptionHandler { _, e -> e.printStackTrace() } }
    private val scope: CoroutineScope by lazy { CoroutineScope(appDispatchers.main + SupervisorJob() + errorHandler) }

    override fun onCreate() {
        super.onCreate()
        scope.launch {
            viewAction { showProgress() }
            val request = getPokemonsUseCase.invoke()
            request.either(
                onSuccess = {
                    viewAction {
                        hideProgress()
                        showMessage("success!")
                    }
                }, onFailure = {
                    viewAction {
                        hideProgress()
                        showError(it.errorMessage ?: "error!")
                    }
                }
            )
        }
    }
}
