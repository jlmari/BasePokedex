package com.jlmari.android.basepokedex.memorydatasource.cache

import com.jlmari.android.basepokedex.domain.models.ErrorModel
import com.jlmari.android.basepokedex.domain.utils.Failure
import com.jlmari.android.basepokedex.domain.utils.Response
import com.jlmari.android.basepokedex.domain.utils.Success
import java.util.*
import java.util.concurrent.TimeUnit

object Cache {

    private const val CACHE_TIME_MINUTES: Long = 10L

    /**
     * Simple cache system. It checks the current time and the [timestamp] from the [model] to see
     * whether is lower than the cache default time or not.
     *
     * @return Success if the value is not expired, Failure with cache error message otherwise.
     */
    fun <T> checkTimestampCache(timestamp: Long, model: T): Response<T, ErrorModel> = model?.let {
        if (Date().time - timestamp < TimeUnit.MINUTES.toMillis(CACHE_TIME_MINUTES)) {
            Success(it as T)
        } else {
            Failure(ErrorModel("cache error"))
        }
    } ?: run { Failure(ErrorModel("cache error")) }
}
