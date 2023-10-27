package com.example.geografiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar

import com.example.geografiapp.DepartamentosActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnSalta: Button
    lateinit var btnJujuy: Button
    lateinit var btnSanLuis: Button
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var departamentosSalta: ArrayList<String> = arrayListOf("San Martín", "Rivadavia", "Salta", "Oran", "La vinia", "Metan")
        //var departamentosJujuy: ArrayList<String> = arrayListOf("San Antonio", "San Pedro", "Santa Bárbara", "Cochinoca", "Humahuaca", "Tilcara")
        //var departamentosSanLuis: ArrayList<String> = arrayListOf("Coronel Pringles", "Chacabuco", "General San Martin", "Belgrano", "Junín", "Ayacucho")

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        btnSalta = findViewById(R.id.botonSalta)
        //btnJujuy = findViewById(R.id.botonJujuy)
        //btnSanLuis = findViewById(R.id.botonSanLuis)
        btnSalta.setOnClickListener {

            val departamentos = departamentosSalta.joinToString(", ")

            val intentDepartamento = Intent(this, DepartamentosActivity::class.java)
            intentDepartamento.putExtra("departamentos", departamentos)

            startActivity(intentDepartamento)

            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_agregar) {
            TODO("Realizar Accion")
        }
        return super.onOptionsItemSelected(item)
    }

}