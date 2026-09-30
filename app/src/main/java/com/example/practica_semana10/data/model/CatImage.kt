package com.example.practica_semana10.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatImage(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String
)