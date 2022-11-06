package com.jlmari.android.basepokedex.utils

import androidx.lifecycle.Lifecycle

/**
 * Method to know if the current [Lifecycle] of the view (activity or fragment) has active state.
 * - [Lifecycle.State.CREATED]
 * - [Lifecycle.State.STARTED]
 * - [Lifecycle.State.RESUMED]
 *
 * @return The [Boolean] that indicates if the current state is valid or not
 */
fun Lifecycle.hasValidActiveState(): Boolean =
    currentState == Lifecycle.State.CREATED
            || currentState == Lifecycle.State.STARTED
            || currentState == Lifecycle.State.RESUMED
