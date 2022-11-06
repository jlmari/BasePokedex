package com.jlmari.android.basepokedex.presentation.base

/**
 * Base MVP contract between any View and its Presenter.
 */
interface BaseContract {

    /**
     * MVP View that defines from what Presenter events we care about.
     */
    interface View {

        fun showProgress()

        fun hideProgress()
    }

    /**
     * MVP Router base interface that defines all those methods that every router must implement.
     * It will be used specifically for navigation purposes and may be implemented in different
     * scopes depending on the navigation level desired.
     */
    interface Router

    /**
     * MVP Presenter that defines from what View events we care about.
     * This is the bridge between the class who implements the View (activity, fragment, dialog...)
     * and the Use Cases.
     * Basically a Presenter will communicate the results of background tasks (like a server request
     * or a database query) that will be affect the UI.
     */
    interface Presenter<in V : View, in R : Router> {

        /**
         * This method will be executed on View's onCreate() method.
         * Attach a [View] to the presenter so the presenter can interact and handle that view.
         *
         * @param view The given [View] to interact with
         */
        fun attachView(view: V)

        /**
         * This method will be executed on View's onCreate() method.
         * It's an optional method, so those Presenters that may need it can override it.
         */
        fun onCreate()

        /**
         * This method will be executed on View's onResume() method.
         * Attach a [Router] to the presenter so the presenter can handle navigation.
         *
         * @param router The given [Router] to interact with
         */
        fun attachRouter(router: R)

        /**
         * This method will be executed on View's onResume() method (if exists).
         * It's an optional method, so those Presenters that may need it can override it.
         */
        fun onResume()

        /**
         * This method will be executed on View's onPause() method.
         * Detach the attached router from the presenter.
         */
        fun detachRouter()

        /**
         * This method will be executed on View's onPause() method (if exists).
         * It's an optional method, so those Presenters that may need it can override it.
         */
        fun onPause()

        /**
         * This method will be executed on View's onDestroy() method.
         */
        fun detachView()

        /**
         * This method will be executed on View's onDestroy() method.
         * It's an optional method, so those Presenters that may need it can override it.
         */
        fun onDestroy()
    }
}
