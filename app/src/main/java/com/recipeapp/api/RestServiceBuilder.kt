package com.recipeapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestServiceBuilder {

    private val httpClient by lazy { OkHttpClient.Builder().build() }

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.RECIPES_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun <T> build(service: Class<T>): T {
        return retrofit.create(service)
    }

}