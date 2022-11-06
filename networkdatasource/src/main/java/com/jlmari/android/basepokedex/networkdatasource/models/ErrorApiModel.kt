package com.jlmari.android.basepokedex.networkdatasource.models

import com.google.gson.annotations.SerializedName

data class ErrorApiModel(
    @SerializedName("error")
    val errorMessage: String
)
