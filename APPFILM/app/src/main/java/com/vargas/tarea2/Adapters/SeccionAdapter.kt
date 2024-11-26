package com.vargas.tarea2.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vargas.tarea2.databinding.SectionItemBinding
import com.vargas.tarea2.DatasClass.Seccion

class SeccionAdapter(private val secciones: List<Seccion>) : RecyclerView.Adapter<SeccionAdapter.SeccionViewHolder>()  {
    //Accedemos a la vista directamente desde binding
    inner class SeccionViewHolder(private val binding: SectionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(seccion: Seccion) {
            //Se muestra el titulo de cada seccion
            binding.textViewTitulo.text = seccion.title
            //Se configura el reciclerView para que se mueva horizontalmente cada seccion
            binding.horizontalReciclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.horizontalReciclerView.adapter = ItemAdapter(seccion.items)
            //Con el ItemAdaprter se mostraran los items de cada seccion es decir que se vincula con la Item vista creada en el layout de image_item.xml
            //Con esto se puede ver cada item de cada seccion en forma horizontal y tambien se puede dezplazar en forma horizontal cada seccion
        }
    }
    //Se crea la vista de cada seccion
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeccionViewHolder {
       //Se infla el adapter correspondiente
        val binding =SectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeccionViewHolder(binding)
    }
    //Se vincula los datos a cada elemento de la lista
    override fun onBindViewHolder(holder: SeccionViewHolder, position: Int) {
        holder.bind(secciones[position])
    }
    //Se devuelve el tamaño de la lista para saber cuantos elementos mostrar
    override fun getItemCount(): Int=secciones.size


}