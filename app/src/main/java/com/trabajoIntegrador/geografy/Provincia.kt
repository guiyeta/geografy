package com.trabajoIntegrador.geografy

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Provincia(
    @Json(name = "id") val id: Int,
    @Json(name = "prov") val prov: String,
    @Json(name = "lat") val lat: String,
    @Json(name = "long") val long: String,

)