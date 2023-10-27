package com.trabajoIntegrador.geografy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DepartamentosActivity: AppCompatActivity() {

    lateinit var tvDepto: TextView

    //var departamentosSalta: ArrayList<String> = arrayListOf("San Martín", "Rivadavia", "Salta", "Oran", "La vinia", "Metan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departamentos)

        tvDepto = findViewById(R.id.depto)

        val datoRecibido = intent.getStringExtra("departamentos")
        //var departamentosSalta: String = "Oran"
        tvDepto.text = datoRecibido.toString()


    }
}

