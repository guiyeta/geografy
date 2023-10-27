package com.example.geografiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar



class LoginActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnIniciar: Button
    lateinit var toolbar: Toolbar

    // Funcion que se ejecuta al iniciar un Activity
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Vinculamos las variables con los IDs correspondientes a la vista
        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        btnIniciar = findViewById(R.id.botonIniciar)
        btnRegistrar = findViewById(R.id.botonRegistrar)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        //Agregamos funcionalidad al Boton
        btnRegistrar.setOnClickListener {
            val intentSignup = Intent(this, SignupActivity::class.java)
            // Cambiamos de pantalla
            startActivity(intentSignup)
            // Eliminamos la Activity actual para sacarla de la Pila
            finish()
        }

        //Agregamos funcionalidad al Boton
        btnIniciar.setOnClickListener {
            var mensaje = "Iniciar Sesion"
            // Obtenemos el dato que se ingreso en la vista
            var nombreUsuario = etUsuario.text.toString()
            if(nombreUsuario.isEmpty() || etPass.text.toString().isEmpty()){
                mensaje+= " - Faltan Datos"
            }else {
                mensaje+= " - Datos OK"
                // Verificamos si esta tildado el CechBox
                // Indicamos a que pantalla queremos ir
                val intentMain = Intent(this, MainActivity::class.java)
                // Agregamos datos que queremos pasar a la proxima pantalla
                intentMain.putExtra("nombre", nombreUsuario)
                // Cambiamos de pantalla
                startActivity(intentMain)
                // Eliminamos la Activity actual para sacarla de la Pila
                finish()
            }
        }
    }


}