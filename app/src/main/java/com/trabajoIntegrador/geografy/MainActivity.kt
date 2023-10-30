package com.trabajoIntegrador.geografy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.geografy.adapter.ProvinciaAdapter
import com.trabajoIntegrador.geografy.endpoint.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProvinciaAdapter
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerMain)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        // Llama a la función para cargar los datos de Provincia en un hilo de fondo
        CoroutineScope(Dispatchers.IO).launch {
            val provincias = obtenerProvinciasDesdeAPI()
            mostrarProvinciasEnUI(provincias)
        }
    }

    private suspend fun obtenerProvinciasDesdeAPI(): List<Provincia> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apis.datos.gob.ar/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MyApi::class.java)
        val call = apiService.getProv()

        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.provincias ?: emptyList()
            } else {
                // Manejar errores si la respuesta no es exitosa
                emptyList()
            }
        } catch (e: Exception) {
            // Manejar errores en caso de fallo en la solicitud
            emptyList()
        }
    }

    private fun mostrarProvinciasEnUI(provincias: List<Provincia>) {
        runOnUiThread {
            adapter = ProvinciaAdapter(provincias)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
    }
    fun onItemSelected(provincia: Provincia) {
        Toast.makeText(this, provincia.nombre, Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_cerrarSesion) {

            val preferencias2 = getSharedPreferences(resources.getString(R.string.credenciales), MODE_PRIVATE)
            preferencias2.edit().putString(resources.getString(R.string.nombre_usuario),null).apply()
            preferencias2.edit().putString(resources.getString(R.string.password_usuario),null).apply()
            var intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)


            return true
            finish()
        }
        if (item.itemId == R.id.item_departamento) {

            var intentDto = Intent(this, DepartamentosActivity::class.java)
            startActivity(intentDto)

            return true
            finish()
        }
        if (item.itemId == R.id.item_provincia) {

            var intentPro = Intent(this, MainActivity::class.java)
            startActivity(intentPro)

            return true
            finish()
        }


        return super.onOptionsItemSelected(item)
    }
}