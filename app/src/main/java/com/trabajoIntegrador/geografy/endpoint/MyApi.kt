package com.trabajoIntegrador.geografy.endpoint

import com.trabajoIntegrador.geografy.Provincia
import com.trabajoIntegrador.geografy.ProvinciaJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MyApi {

//    @Headers("bearertoken", "")
    @GET("/georef/api/provincias")
    fun getProv(): Call<ProvinciaJson>
}