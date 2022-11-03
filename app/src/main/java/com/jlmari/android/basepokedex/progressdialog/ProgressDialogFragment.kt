package com.jlmari.android.basepokedex.progressdialog

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jlmari.android.basepokedex.R
import com.jlmari.android.basepokedex.utils.safeShow

class ProgressDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        @Suppress("DEPRECATION")
        with(ProgressDialog(context)) {
            // Configure the progress dialog
            isIndeterminate = true
            window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            safeShow()
            setContentView(R.layout.dialog_progress)
            // Return the modified dialog instance
            this
        }
}
