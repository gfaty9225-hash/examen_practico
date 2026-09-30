package com.example.practicasemana10.data.api

import com.example.practica_semana10.data.model.CatBreed
import com.example.practica_semana10.data.model.CatImage
import retrofit2.http.GET

import retrofit2.http.Query

interface CatApiService {

    // 1. Endpoint para consultar la colección de razas
    @GET("v1/breeds")
    suspend fun getBreeds(): List<CatBreed>

    // 2. Endpoint para consultar el detalle/imágenes de una raza específica
    @GET("v1/images/search")
    suspend fun getImagesByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 10
    ): List<CatImage>
}