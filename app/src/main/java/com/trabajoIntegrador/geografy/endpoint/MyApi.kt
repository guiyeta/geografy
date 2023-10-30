package com.trabajoIntegrador.geografy.endpoint

import com.trabajoIntegrador.geografy.DepartamentoJson
import com.trabajoIntegrador.geografy.Provincia
import com.trabajoIntegrador.geografy.ProvinciaJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MyApi {

//    @Headers("bearertoken", "")
    @GET("/georef/api/provincias")
    fun getProv(): Call<ProvinciaJson>
    @GET("/georef/api/departamentos?max=150")
    fun getDep(@Query("provincia") provincia: String): Call<DepartamentoJson>
}