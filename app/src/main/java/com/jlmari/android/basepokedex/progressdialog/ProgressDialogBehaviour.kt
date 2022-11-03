package com.jlmari.android.basepokedex.progressdialog

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.jlmari.android.basepokedex.utils.hasValidActiveState

/**
 * Behaviour that should be implemented for all the views that requires a progress [DialogFragment].
 */
interface ProgressDialogBehaviour {

    var progressDialog: DialogFragment?
    var progressDialogFragmentManager: FragmentManager
    var progressDialogLifecycle: Lifecycle

    /**
     * Method to show the loading inside the [DialogFragment].
     */
    fun showProgressDialog() {
        val dialog = progressDialog ?: DialogFragment()
        dialog.show(progressDialogFragmentManager, PROGRESS_DIALOG_TAG)
    }

    /**
     * Method to hide the loading safely.
     */
    fun dismissProgressDialog() {
        if (progressDialogLifecycle.hasValidActiveState()) {
            progressDialog?.dismissAllowingStateLoss()
        }
    }

    companion object {
        private const val PROGRESS_DIALOG_TAG = "PROGRESS_DIALOG_TAG"
    }
}
