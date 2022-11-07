package com.jlmari.android.basepokedex.data.utils

import com.jlmari.android.basepokedex.domain.utils.Failure
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.domain.utils.Success
import com.jlmari.android.basepokedex.domain.utils.either

/**
 * Single source of truth pattern. Database serves as SSOT in this case. Network calls are used
 * to store the values in the database.
 */
suspend fun <V, E> singleSourceOfTruth(
    memoryDataSource: suspend (Unit) -> Response<V, E>,
    networkDataSource: suspend (Unit) -> Response<V, E>,
    memoryCallback: suspend (V) -> Response<V, E>
): Response<V, E> =
    memoryDataSource(Unit).either(
        onSuccess = { Success(it) },
        onFailure = {
            networkDataSource(Unit).either(
                onSuccess = { networkResult -> memoryCallback.invoke(networkResult) },
                onFailure = { error -> Failure(error) })
        }
    )
