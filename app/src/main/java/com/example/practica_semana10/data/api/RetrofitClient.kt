// data/api/RetrofitClient.kt
package com.example.practicasemana10.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "https://api.thecatapi.com/"

    private val json = Json {
        ignoreUnknownKeys = true // Evita errores al recibir campos no mapeados
    }

    val apiService: CatApiService by lazy {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(CatApiService::class.java)
    }
}