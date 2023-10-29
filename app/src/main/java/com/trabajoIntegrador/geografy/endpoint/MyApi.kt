package com.trabajoIntegrador.geografy.endpoint

import com.trabajoIntegrador.geografy.Provincia
import retrofit2.Call
import retrofit2.http.GET
interface MyApi {

    @GET("/georef/api/provincias")
    fun getCharacters(): Call<List<Provincia>>
}