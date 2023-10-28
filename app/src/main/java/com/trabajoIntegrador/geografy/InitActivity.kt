package com.trabajoIntegrador.geografy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.trabajoIntegrador.geografy.LoginActivity
import com.trabajoIntegrador.geografy.R


class InitActivity : AppCompatActivity() {


    lateinit var btnIniciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            val intentLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentLoginActivity)


        }


    }

}