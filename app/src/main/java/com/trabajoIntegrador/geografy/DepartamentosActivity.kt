package com.trabajoIntegrador.geografy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.geografy.adapter.DepartamentoAdapter
import com.trabajoIntegrador.geografy.endpoint.MyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DepartamentosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DepartamentoAdapter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamentos)

        recyclerView = findViewById(R.id.recyclerDepartamentos)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        // Llama a la función para cargar los datos de Departamento en un hilo de fondo
        CoroutineScope(Dispatchers.IO).launch {
            val departamentos = obtenerDepartamentosDesdeAPI("Buenos Aires")
            mostrarDepartamentosEnUI(departamentos)
        }
    }

    private suspend fun obtenerDepartamentosDesdeAPI(nombreProvincia: String): List<Departamento> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apis.datos.gob.ar/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MyApi::class.java)
        val call = apiService.getDep(nombreProvincia)

        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.departamentos ?: emptyList()
            } else {
                // Manejar errores si la respuesta no es exitosa
                emptyList()
            }
        } catch (e: Exception) {
            // Manejar errores en caso de fallo en la solicitud
            emptyList()
        }
    }

    private fun mostrarDepartamentosEnUI(departamentos: List<Departamento>) {
        runOnUiThread {
            adapter = DepartamentoAdapter(departamentos)
            recyclerView.layoutManager = LinearLayoutManager(this@DepartamentosActivity)
            recyclerView.adapter = adapter
        }
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

