package com.trabajoIntegrador.geografy.endpoint

import com.trabajoIntegrador.geografy.Provincia
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MyApi {

//    @Headers("bearertoken", "")
    @GET("/georef/api/provincias?nombre")
    fun getProv(): Call<List<Provincia>>
}