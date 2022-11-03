package com.jlmari.android.basepokedex.networkdatasource.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jlmari.android.basepokedex.networkdatasource.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(isDebug: Boolean) {

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val client by lazy {
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (isDebug) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/"
    }
}
