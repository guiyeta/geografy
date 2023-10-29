package com.trabajoIntegrador.geografy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide

class ProvinciaActivity : AppCompatActivity() {

    lateinit var im: ImageView
    //var personajels: List<Personaje> = PersonajesProvider.personajeList // lista con personajes


    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle: Bundle? = intent.extras // datos extras que envie a esta activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provincia)

        val foto = bundle?.getString("img") // obtengo los datos extras

        /* val i = personajels.indexOfFirst { it.firstName == nomPer }// busco el dato extra en la lista
         if (i != -1) {
             val image = personajels[i].imageUrl*/
        im= findViewById(R.id.IvProvincia)

        Glide.with(im.context).load(foto).into(im)// cargo la irl de la foto

    }

}