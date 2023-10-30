package com.trabajoIntegrador.geografy.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trabajoIntegrador.geografy.Provincia
import com.trabajoIntegrador.geografy.R


class ProvinciaHolder(view: View) : RecyclerView.ViewHolder(view) {
    //push
    val nombre= view.findViewById<TextView>(R.id.nomProvincia)
   // val latitud= view.findViewById<TextView>(R.id.latProv)
  //  val longitud= view.findViewById<TextView>(R.id.lonProv)




    fun render(provinciaModel: Provincia) {
        nombre.text = provinciaModel.nombre
 //       latitud.text = provinciaModel.lat
  //      longitud.text = provinciaModel.long


    }
}