package com.jlmari.android.basepokedex.utils

import android.content.Context
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
