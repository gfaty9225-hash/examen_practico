package com.example.practica_semana10.data.api

import com.example.practica_semana10.data.model.CatBreed
import com.example.practica_semana10.data.model.CatImage
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/breeds")
    suspend fun getBreeds(): List<CatBreed>

    @GET("v1/images/search")
    suspend fun getImagesByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 10
    ): List<CatImage>
}