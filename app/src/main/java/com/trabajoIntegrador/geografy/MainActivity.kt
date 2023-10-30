package com.trabajoIntegrador.geografy


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.geografy.adapter.ProvinciaAdapter
import com.trabajoIntegrador.geografy.configuraciones.RetrofitClient
import com.trabajoIntegrador.geografy.endpoint.MyApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    //Variables
    private lateinit var filtro: EditText
    lateinit var toolbar: Toolbar
    private  var provinciaList: MutableList<Provincia> = mutableListOf()
    private lateinit var adapter: ProvinciaAdapter
    private lateinit var tvServicio : TextView
    /////////

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        Log.d("debug","antes de la api")

        //comienza API
        initApi()

        //filtrado de personajes por sus atributos
        /*filtro=findViewById(R.id.scFilter)



        filtro.addTextChangedListener { userFilter ->
            val searchText = userFilter.toString().lowercase()

            val provinciaFiltrada = provinciaList.filter { provincia ->
                provincia.prov.lowercase().contains(searchText) ||
                        provincia.lat.lowercase().contains(searchText) ||
                        provincia.long.lowercase().contains(searchText)

            }

            adapter.updateProvincia(provinciaFiltrada)
        }*/



        Log.d("debug","antes del recycler")

        Log.d("debug","despues del recycler")
        //toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

    }



    private fun initApi() {
        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        val callGetProv = api.getProv()

        callGetProv.enqueue(object : retrofit2.Callback<List<Provincia>> {

            override fun onResponse(
                call: Call<List<Provincia>>,
                response: Response<List<Provincia>>
            ){
                Log.d("debug", "variables del onResponse")
                val provincia = response.body()
                Log.d("debug", "despues de la val ${provincia?.get(1)?.nombre}")
                if (provincia != null) {

                    provinciaList.addAll(provincia)

                    Log.d("Error", "entrando al if de provincias")

                    initRecyclerView()

                } else {
                    Log.d("debug", "provincias nulos")
                }


            }

            override fun onFailure(call: Call<List<Provincia>>, t: Throwable) {
                Log.e("error", "error al llamar a la api")
            }


        })

    }

    private fun initRecyclerView() {
        val recyclerView= findViewById<RecyclerView>(R.id.recyclerMain)
        adapter = ProvinciaAdapter(provinciaList)

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter


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

            var preferencias =
                getSharedPreferences(resources.getString((R.string.credenciales)), MODE_PRIVATE)
            preferencias.edit().putString(resources.getString(R.string.nombre_usuario), "").apply()
            preferencias.edit().putString(resources.getString(R.string.password_usuario), "")
                .apply()
            var intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }
       /* if (item.itemId == R.id.item_libro) {
            var intentBook = Intent(this, BooksActivity::class.java)
            startActivity(intentBook)
            finish()
        }
        if (item.itemId == R.id.item_house) {
            var intentHouse = Intent(this, HouseActivity::class.java)
            startActivity(intentHouse)
            finish()

            val preferencias =
                getSharedPreferences(resources.getString((R.string.credenciales)), MODE_PRIVATE)
            val editar = preferencias.edit()
            editar.putString(resources.getString(R.string.nombre_usuario), "")
            editar.putString(resources.getString(R.string.password_usuario), "")
            editar.apply()

        }*/
        return super.onOptionsItemSelected(item)
    }



}