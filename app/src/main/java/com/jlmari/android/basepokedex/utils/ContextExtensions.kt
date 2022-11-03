package com.jlmari.android.basepokedex.utils

import android.content.Context
import com.jlmari.android.basepokedex.application.PokeApp

/**
 * Value of the Application [Context] of this specific [PokeApp].
 */
val Context.app: PokeApp
    get() = applicationContext as PokeApp
