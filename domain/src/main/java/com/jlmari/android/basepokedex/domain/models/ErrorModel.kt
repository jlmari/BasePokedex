package com.jlmari.android.basepokedex.domain.models

data class ErrorModel(
    val errorMessage: String? = null,
    val errorCode: Int? = null
) : Throwable()
