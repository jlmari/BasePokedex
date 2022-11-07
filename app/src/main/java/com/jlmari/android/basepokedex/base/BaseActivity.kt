package com.jlmari.android.basepokedex.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jlmari.android.basepokedex.application.di.AppComponent
import com.jlmari.android.basepokedex.presentation.base.BaseContract
import com.jlmari.android.basepokedex.progressdialog.ProgressDialogBehaviour
import com.jlmari.android.basepokedex.progressdialog.ProgressDialogFragment
import com.jlmari.android.basepokedex.utils.app
import java.lang.reflect.Modifier
import javax.inject.Inject

/**
 * Base activity that must be extended by all activities.
 */
abstract class BaseActivity<in V : BaseContract.View, in R : BaseContract.Router, P : BaseContract.Presenter<V, R>>
    : AppCompatActivity(), BaseContract.View, BaseContract.Router, ProgressDialogBehaviour {

    /**
     * The [BaseContract.Presenter] that will be injected.
     */
    @Inject
    @VisibleForTesting(otherwise = Modifier.PROTECTED)
    lateinit var presenter: P

    /**
     * Specify the layout resource ID to be inflated in the [BaseActivity.onCreate] method.
     */
    abstract val layoutResId: Int

    /**
     * Add the required initializations to inject a custom progress dialog for all activities.
     * It will be used for loading purposes.
     */
    override var progressDialog: DialogFragment? = ProgressDialogFragment()
    override lateinit var progressDialogFragmentManager: FragmentManager
    override var progressDialogLifecycle = lifecycle

    /**
     * [AppCompatActivity] lifecycle method onCreate().
     * 1) Inject dependencies (or recover presenter if already have their instance)
     * 2) Get bundle data from any possible option
     * 3) Attach itself as view and call onCreate() to its presenter
     * 4) Init views and setup listeners
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        if (lastCustomNonConfigurationInstance == null) {
            // Init dependencies (if first time)
            injectDependencies(baseContext?.app?.component)
        } else {
            // Get presenter from custom instance retained (if not)
            @Suppress("UNCHECKED_CAST")
            presenter = lastCustomNonConfigurationInstance as P
        }

        // Apply regular onCreate method
        super.onCreate(savedInstanceState)

        // Get extras
        retrieveBundleData(savedInstanceState ?: intent.extras ?: Bundle.EMPTY)

        // Set layout
        setContentView(layoutResId)

        // Notify presenter
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)

        // Call onCreate of the presenter (only if it already existed)
        if (lastCustomNonConfigurationInstance == null) {
            presenter.onCreate()
        }

        // Initialize progress dialog for loading purposes
        progressDialogFragmentManager = supportFragmentManager

        // Init views
        initViews()

        // Setup listeners
        setupListeners()
    }

    /**
     * [AppCompatActivity] lifecycle method onRetainCustomNonConfigurationInstance().
     * Recover presenter.
     */
    override fun onRetainCustomNonConfigurationInstance() = presenter

    /**
     * [AppCompatActivity] lifecycle method onResume().
     * Attach itself as router and call onResume to its presenter.
     */
    override fun onResume() {
        super.onResume()

        // Notify presenter
        @Suppress("UNCHECKED_CAST")
        presenter.attachRouter(this as R)
        presenter.onResume()
    }

    /**
     * [AppCompatActivity] lifecycle method onPause().
     * Detach router and call onPause to its presenter.
     */
    override fun onPause() {
        // Notify presenter
        presenter.detachRouter()
        presenter.onPause()

        super.onPause()
    }

    /**
     * [AppCompatActivity] lifecycle method onDestroy().
     * Detach view and call onDestroy to its presenter.
     */
    override fun onDestroy() {
        // Notify presenter
        presenter.detachView()
        presenter.onDestroy()

        super.onDestroy()
    }

    /**
     * Setup the object graph and inject the dependencies needed on each activity.
     *
     * @param appComponent The required [AppComponent] to inject all the needed dependencies
     */
    abstract fun injectDependencies(appComponent: AppComponent?)

    /**
     * Method to get all the extra data sent via Intent's from another context.
     * It's an optional method since maybe there is not extra data needed.
     *
     * @param bundle The required [Bundle] where the data is compressed
     */
    open fun retrieveBundleData(bundle: Bundle) {
    }

    /**
     * This method is executed on [BaseActivity.onCreate] to init views.
     * It's an optional method, so those extending activities that may need it can override it.
     */
    open fun initViews() {
    }

    /**
     * This method is executed on [BaseActivity.onCreate] to setup view listeners.
     * It's an optional method, so those extending activities that may need it can override it.
     */
    open fun setupListeners() {
    }

    /**
     * This method overrides the default dispatch touch event from the Android [AppCompatActivity].
     * It clears the focus of [EditText] when a touch event is triggered outside.
     *
     * @param event The [MotionEvent] executed by the user when touching the screen
     * @return The [Boolean] with the default behaviour of [dispatchTouchEvent]
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        // Clear Edit Text focus if any
        if (event.action == MotionEvent.ACTION_DOWN) {
            (currentFocus as? EditText)?.run {
                val outRect = Rect().apply { getGlobalVisibleRect(this) }
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    clearFocus()
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
                        ?.hideSoftInputFromWindow(windowToken, 0)
                }
            }
        }
        // Return default behaviour
        return super.dispatchTouchEvent(event)
    }

    override fun showProgress() {
        showProgressDialog()
    }

    override fun hideProgress() {
        dismissProgressDialog()
    }
}
