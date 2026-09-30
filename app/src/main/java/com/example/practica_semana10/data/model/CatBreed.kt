package com.example.practica_semana10.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatBreed(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("origin") val origin: String? = "Origen no especificado",
    @SerialName("temperament") val temperament: String? = "Sin datos de temperamento",
    @SerialName("description") val description: String? = "Sin descripción"
)