package com.jlmari.android.basepokedex.presentation.base

import com.jlmari.android.basepokedex.domain.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.*

/**
 * Base presenter that must be extended by all presenters.
 */
abstract class BasePresenter<V : BaseContract.View, R : BaseContract.Router>(appDispatchers: AppDispatchers? = null) :
    BaseContract.Presenter<V, R> {

    private var view: V? = null
    private val pendingViewTransactions: Queue<(V) -> Unit> = LinkedList()
    private var router: R? = null
    private val pendingRouterTransactions: Queue<(R) -> Unit> = LinkedList()

    /**
     * Manage errors using Kotlin Coroutines and create a new [CoroutineScope] for async calls.
     */
    private val errorHandler: CoroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { _, e -> e.printStackTrace() }
    }
    protected val scope: CoroutineScope by lazy {
        if (appDispatchers != null) {
            CoroutineScope(appDispatchers.main + SupervisorJob() + errorHandler)
        } else {
            CoroutineScope(SupervisorJob() + errorHandler)
        }
    }

    /**
     * Attach a View [V] to the presenter so the presenter can interact and handle that view.
     *
     * @param view The given View [V] to interact with
     */
    override fun attachView(view: V) = synchronized(this) {
        this.view = view
        while (!pendingViewTransactions.isEmpty()) {
            pendingViewTransactions.poll()(view)
        }
    }

    /**
     * Optional lifecycle onCreate() method that will be implemented only for the presenters who need it.
     */
    override fun onCreate() {
    }

    /**
     * Attach a Router [R] to the presenter so the presenter can handle navigation.
     *
     * @param router The given Router [R] to interact with
     */
    override fun attachRouter(router: R) = synchronized(this) {
        this.router = router
        while (!pendingRouterTransactions.isEmpty()) {
            pendingRouterTransactions.poll()(router)
        }
    }

    /**
     * Optional lifecycle onResume() method that will be implemented only for the presenters who need it.
     */
    override fun onResume() {
    }

    /**
     * Detach the attached router from the presenter.
     */
    override fun detachRouter() = synchronized(this) {
        router = null
    }

    /**
     * Optional lifecycle onPause() method that will be implemented only for the presenters who need it.
     */
    override fun onPause() {
    }

    /**
     * Detach the attached view to the presenter.
     */
    override fun detachView() = synchronized(this) {
        view = null
    }

    /**
     * Optional lifecycle onDestroy() method that will be implemented only for the presenters who need it.
     */
    override fun onDestroy() {
    }

    /**
     * Perform an action over a view. If the view is available, the action will be immediately
     * performed; otherwise, the action will be enqueued until a new available view had been set.
     */
    protected fun viewAction(action: V.() -> Unit) = synchronized(this) {
        view?.let { action(it) } ?: pendingViewTransactions.add(action)
    }

    /**
     * Perform an action over a router. If the router is available, the action will be immediately
     * performed; otherwise, the action will be enqueued until a new available router had been set.
     */
    protected fun routerAction(action: R.() -> Unit) = synchronized(this) {
        router?.let { action(it) } ?: pendingRouterTransactions.add(action)
    }
}
