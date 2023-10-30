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
import com.trabajoIntegrador.geografy.Departamento
import com.trabajoIntegrador.geografy.R

class DepartamentoAdapter(private val departamentos: List<Departamento>) : RecyclerView.Adapter<DepartamentoAdapter.DepartamentoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartamentoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_departamentos, parent, false)
        return DepartamentoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DepartamentoViewHolder, position: Int) {
        val departamento = departamentos[position]
        holder.render(departamento)
    }

    override fun getItemCount(): Int {
        return departamentos.size
    }

    inner class DepartamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombre= itemView.findViewById<TextView>(R.id.nomDepartamento)
        val latitud= itemView.findViewById<TextView>(R.id.provDepartamento)
        val longitud= itemView.findViewById<TextView>(R.id.idDepartamento)




        fun render(departamentoModel: Departamento) {
            nombre.text = departamentoModel.nombre
            latitud.text = departamentoModel.provincia.nombre
            longitud.text = departamentoModel.id


        }
    }
}
