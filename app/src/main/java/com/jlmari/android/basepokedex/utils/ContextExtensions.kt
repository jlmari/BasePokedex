package com.jlmari.android.basepokedex.utils

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import com.jlmari.android.basepokedex.application.PokeApp

/**
 * Value of the Application [Context] of this specific [PokeApp].
 */
val Context.app: PokeApp
    get() = applicationContext as PokeApp

/**
 * Show long Toast with message.
 */
fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

/**
 * Transforms values from dp to pixels
 * @param dps float representing a dp value
 * @return integer value with the dp value of dps in pixels
 */
fun Context.dpToPx(dps: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, resources.displayMetrics).toInt()