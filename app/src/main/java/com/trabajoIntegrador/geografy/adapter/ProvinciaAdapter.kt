package com.trabajoIntegrador.geografy.adapter

import com.trabajoIntegrador.geografy.Provincia
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.geografy.ProvinciaActivity
import com.trabajoIntegrador.geografy.R

class ProvinciaAdapter (

    private var provinciaList: List<Provincia>
    ) : RecyclerView.Adapter<ProvinciaHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaHolder {
            Log.d("debug","entrando oncreateviewholder")
            val layaoutInFlater = LayoutInflater.from(parent.context)
            return ProvinciaHolder(layaoutInFlater.inflate(R.layout.item_provincia, parent, false))
            Log.d("debug","despues del return oncreateviewholder")
        }

        override fun getItemCount(): Int {
            return provinciaList.size
        }

        override fun onBindViewHolder(holder: ProvinciaHolder, position: Int) {
            val item = provinciaList[position]
            Log.d("debug","despues item=provinciaList")
            holder.render(item)
            val activity = holder.itemView.context
            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context, item.prov, Toast.LENGTH_SHORT).show()

                val intentProvincia = Intent(it.context, ProvinciaActivity::class.java)
                activity.startActivity(intentProvincia)

            }
        }

        fun updateProvincia(personajeList: List<Provincia>) {
            this.provinciaList = personajeList
            notifyDataSetChanged()
        }
}
