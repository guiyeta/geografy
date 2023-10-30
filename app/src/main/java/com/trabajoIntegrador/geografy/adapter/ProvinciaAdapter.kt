package com.trabajoIntegrador.geografy.adapter

import com.trabajoIntegrador.geografy.Provincia
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.geografy.ProvinciaActivity
import com.trabajoIntegrador.geografy.R

class ProvinciaAdapter(private val provincias: List<Provincia>) : RecyclerView.Adapter<ProvinciaAdapter.ProvinciaViewHolder>() {
    lateinit var prov: TextView
    lateinit var lati: TextView
    lateinit var longi: TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_provincia, parent, false)
        return ProvinciaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        val provincia = provincias[position]
        holder.render(provincia)
    }

    override fun getItemCount(): Int {
        return provincias.size
    }

    inner class ProvinciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombre= itemView.findViewById<TextView>(R.id.nomprov)
         val latitud= itemView.findViewById<TextView>(R.id.lat)
          val longitud= itemView.findViewById<TextView>(R.id.lon)




        fun render(provinciaModel: Provincia) {
            nombre.text = provinciaModel.nombre
            latitud.text = provinciaModel.centroide.lat.toString()
            longitud.text = provinciaModel.centroide.lon.toString()


        }
    }
}
