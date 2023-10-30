package com.trabajoIntegrador.geografy

data class DepartamentoJson(
    val cantidad: Int,
    val departamentos: List<Departamento>,
    val inicio: Int,
    val parametros: Parametros,
    val total: Int
)

data class Departamento(
    val centroide: Centroide2,
    val id: String,
    val nombre: String,
    val provincia: Provincia2
)

data class Centroide2(
    val lat: Double,
    val lon: Double
)

data class Parametros(
    val max: Int,
    val provincia: String
)

data class Provincia2(
    val id: String,
    val nombre: String
)