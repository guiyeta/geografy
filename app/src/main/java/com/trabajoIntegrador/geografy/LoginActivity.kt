package com.trabajoIntegrador.geografy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.trabajoIntegrador.datos.geografy.AppDatabase
import com.trabajoIntegrador.datos.geografy.Usuario


class LoginActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnIniciar: Button
    lateinit var toolbar: Toolbar
    private lateinit var cbRecordar: CheckBox

    // Funcion que se ejecuta al iniciar un Activity
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Vinculamos las variables con los IDs correspondientes a la vista
        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        btnIniciar = findViewById(R.id.botonIniciar)
        btnRegistrar = findViewById(R.id.botonRegistrar)
        cbRecordar = findViewById(R.id.RecordarUsuario)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        val preferencias = getSharedPreferences(resources.getString(R.string.credenciales), MODE_PRIVATE)
        val usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario),null)
        val passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario),null)

        if(usuarioGuardado != null && passwordGuardado != null){
            inicio()
        }

        //Agregamos funcionalidad al Boton
        btnRegistrar.setOnClickListener {
            val intentSignup = Intent(this, SignupActivity::class.java)
            startActivity(intentSignup)
            Toast.makeText(this, "Registrar usuario",Toast.LENGTH_SHORT).show()
        }

        //Agregamos funcionalidad al Boton
        btnIniciar.setOnClickListener {
            var mensaje = "Iniciar Sesion"
            // Obtenemos el dato que se ingreso en la vista
            var nombreUsuario = etUsuario.text.toString()
            var contraseñaUsuario = etPass.text.toString()
            if(nombreUsuario.isEmpty() || contraseñaUsuario.isEmpty()){
                mensaje+= " - Faltan Datos"
            }else {
                val usuarios: MutableList<Usuario> = getUsuarios()
                var check = 0

                for(item in usuarios){
                    if(item.correo == nombreUsuario && item.pass == contraseñaUsuario){
                        mensaje+= " - DATOS CORRECTOS"
                        check = 1
                    }
                    if(item.correo == nombreUsuario && item.pass != contraseñaUsuario){
                        check = -1
                        mensaje+= " - CONTRASEÑA INCORRECTA"
                    }
                }

                if(check == 0){
                    mensaje+= " - NO HAY UN USUARIO REGISTRADO CON ESTOS DATOS"
                }

                if(cbRecordar.isChecked && check == 1){
                    val preferencias2 = getSharedPreferences(resources.getString(R.string.credenciales), MODE_PRIVATE)
                    preferencias2.edit().putString(resources.getString(R.string.nombre_usuario),nombreUsuario).apply()
                    preferencias2.edit().putString(resources.getString(R.string.password_usuario), contraseñaUsuario).apply()
                    mensaje+= " - Recordar Usuario"
                }

                if(check == 1){
                    inicio()
                }
            }
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
        }
        cbRecordar.setOnClickListener {
            val intentNotificacion = Intent(this, Notificacion::class.java)
            if(cbRecordar.isChecked){
                intentNotificacion.putExtra("seleccionado",true)
                ContextCompat.startForegroundService(this,intentNotificacion)
            }else{
                stopService(intentNotificacion)
            }

        }
    }
    private fun inicio() {
        val intentLista = Intent(this, MainActivity::class.java)
        startActivity(intentLista)
        finish()
    }
    private fun getUsuarios(): MutableList<Usuario> {
        val usuarios: MutableList<Usuario> = ArrayList()
        val bdd = AppDatabase.getDatabase(this)
        usuarios.addAll(bdd.usuarioDao().getAll())
        return usuarios
    }

}