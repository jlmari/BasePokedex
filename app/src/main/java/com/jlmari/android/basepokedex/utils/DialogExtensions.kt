package com.jlmari.android.basepokedex.utils

import android.app.Dialog
import android.util.Log
import android.view.WindowManager

/**
 * Method to show a [Dialog] safely.
 * That way it cannot fail if the activity where the dialog wants to show is no longer available.
 */
fun Dialog.safeShow() {
    try {
        show()
    } catch (e: WindowManager.BadTokenException) {
        Log.e("BadTokenException", e.toString())
    }
}
