package com.trabajoIntegrador.geografy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProvinciaActivity : AppCompatActivity() {

    lateinit var prov: TextView
    var provinciaLst: List<Provincia> = ProvinciasProvider.provinciaList // lista con personajes


    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle: Bundle? = intent.extras // datos extras que envie a esta activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provincia)

        val titulo = bundle?.getString("titulo") // obtengo los datos extras

        /* val i = personajels.indexOfFirst { it.firstName == nomPer }// busco el dato extra en la lista
         if (i != -1) {
             val image = personajels[i].imageUrl*/
        prov= findViewById(R.id.tvProvincia)

        //Glide.with(prov.context).load(titulo).into(prov)// cargo la irl de la foto

    }

}