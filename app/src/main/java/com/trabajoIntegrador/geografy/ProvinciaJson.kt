package com.trabajoIntegrador.geografy

import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProvinciaJson(
    val cantidad: Int,
    val inicio: Int,
    val parametros: Map<String, Any>, // Puede ser un mapa si no sabes la estructura exacta de los parámetros
    val provincias: List<Provincia>,
    val total: Int
)


data class Provincia(
    val centroide: Centroide,
    val id: String,
    val nombre: String
)

data class Centroide(
    val lat: Double,
    val lon: Double
)