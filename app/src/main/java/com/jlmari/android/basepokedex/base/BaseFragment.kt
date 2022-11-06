package com.jlmari.android.basepokedex.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.base.viewbinding.VBHelpers
import com.jlmari.android.basepokedex.base.viewbinding.VBHelpersImpl
import com.jlmari.android.basepokedex.presentation.base.BaseContract
import com.jlmari.android.basepokedex.progressdialog.ProgressDialogBehaviour
import com.jlmari.android.basepokedex.progressdialog.ProgressDialogFragment
import com.jlmari.android.basepokedex.utils.app
import java.lang.reflect.Modifier
import java.util.*
import javax.inject.Inject

/**
 * Base fragment that must be extended by all fragments that want to use view binding or wants to
 * handle configuration changes.
 *
 * View binding has some benefits over kotlin synthetic, especially the ease of use after reinflating
 * layouts on onConfigurationChanged, that's why we only handle screen rotation and configuration
 * changes on this new [BaseFragment].
 */
abstract class BaseFragment<in V : BaseContract.View, in R : BaseContract.Router, P : BaseContract.Presenter<V, R>, VB : ViewBinding>(
    private val vbHelperImpl: VBHelpersImpl<VB> = VBHelpersImpl()
) : Fragment(), BaseContract.View, BaseContract.Router, ProgressDialogBehaviour, VBHelpers<VB> by vbHelperImpl {

    /**
     * The [BaseContract.Presenter] that will be injected.
     */
    @Inject
    @VisibleForTesting(otherwise = Modifier.PROTECTED)
    lateinit var presenter: P

    /**
     * Specify the binding inflater to be used in the [BaseFragment.onCreateView] method.
     */
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    /**
     * Add the required initializations to inject a custom progress dialog for all fragments.
     * It will be used for loading purposes.
     */
    override var progressDialog: DialogFragment? = ProgressDialogFragment()
    override lateinit var progressDialogFragmentManager: FragmentManager
    override var progressDialogLifecycle = lifecycle

    /**
     * [Fragment] lifecycle method onCreate(). Inject dependencies.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Inject dependencies
        injectDependencies(activity?.app?.component)

        // Apply regular onCreate method
        super.onCreate(savedInstanceState)

        // Initialize progress dialog for loading purposes
        progressDialogFragmentManager = childFragmentManager
    }

    /**
     * [Fragment] lifecycle method onCreateView(). Set layout resource.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vbHelperImpl.updateBinding(bindingInflater(inflater, container, false))
        return getBinding().root
    }

    /**
     * [Fragment] lifecycle method onViewCreated().
     * 1) Get bundle data from any possible option
     * 2) Attach itself as view and call onCreate() to its presenter
     * 3) Init views and setup listeners
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get extras
        retrieveBundleData()

        // Notify presenter
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)
        presenter.onCreate()

        // Init views
        initViews()

        // Setup listeners
        setupListeners()
    }

    /**
     * [Fragment] lifecycle method onResume().
     * Attach itself as router and call onResume() to its presenter.
     */
    override fun onResume() {
        super.onResume()

        // Notify presenter
        @Suppress("UNCHECKED_CAST")
        presenter.attachRouter(this as R)
        presenter.onResume()
    }

    /**
     * [Fragment] lifecycle method onPause().
     * Detach router and call onPause() to its presenter.
     */
    override fun onPause() {
        // Notify presenter
        presenter.detachRouter()
        presenter.onPause()

        super.onPause()
    }

    /**
     * [Fragment] lifecycle method onDestroyView().
     * Detach view and call onDestroy() to its presenter.
     */
    override fun onDestroyView() {
        // Notify presenter
        presenter.detachView()
        presenter.onDestroy()

        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        vbHelperImpl.destroyBinding()
    }

    /**
     * Setup the object graph and inject the dependencies needed on each fragment.
     *
     * @param appComponent The required [AppComponent] to inject all the needed dependencies
     */
    abstract fun injectDependencies(appComponent: AppComponent?)

    /**
     * Method to get all the extra data sent via Intent's from another context.
     * It's an optional method since maybe there is not extra data needed.
     */
    open fun retrieveBundleData() {
    }

    /**
     * This method is executed on [BaseFragment.onViewCreated] to init views.
     * It's an optional method, so those extending fragments that may need it can override it.
     */
    open fun initViews() {
    }

    /**
     * This method is executed on [BaseFragment.onCreate] to setup view listeners.
     * It's an optional method, so those extending fragments that may need it can override it.
     */
    open fun setupListeners() {
    }

    override fun showProgress() {
        showProgressDialog()
    }

    override fun hideProgress() {
        dismissProgressDialog()
    }
}
