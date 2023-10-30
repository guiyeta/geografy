package com.trabajoIntegrador.geografy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.geografy.Provincia
import com.trabajoIntegrador.geografy.ProvinciaJson
import com.trabajoIntegrador.geografy.R
import com.trabajoIntegrador.geografy.adapter.ProvinciaAdapter
import com.trabajoIntegrador.geografy.endpoint.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProvinciaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Asegúrate de que el diseño tenga un RecyclerView con el ID "recyclerView"

        recyclerView = findViewById(R.id.recyclerMain) // Reemplaza con el ID correcto del RecyclerView

        // Aquí realizarías la llamada a la API para obtener los datos de Provincia
        // Supongamos que utilizas Retrofit para la llamada a la API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apis.datos.gob.ar/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MyApi::class.java) // Define una interfaz ApiService para tus llamadas a la API

        val call = apiService.getProv() // Define el método obtenerProvincias() en tu ApiService

        call.enqueue(object : Callback<ProvinciaJson> {
            override fun onResponse(call: Call<ProvinciaJson>, response: Response<ProvinciaJson>) {
                if (response.isSuccessful) {
                    val provincias = response.body()?.provincias ?: emptyList()
                    adapter = ProvinciaAdapter(provincias)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = adapter
                } else {
                    // Manejar errores si la respuesta no es exitosa
                }
            }

            override fun onFailure(call: Call<ProvinciaJson>, t: Throwable) {
                // Manejar errores en caso de fallo en la solicitud
            }

        })
    }
}