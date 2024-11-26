package com.vargas.tarea2.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vargas.tarea2.databinding.ImageItemBinding
import com.vargas.tarea2.DatasClass.Item

// Se le pasa al constructor una lista de objetos Item, que son los elementos que se mostrarán en el
//RecyclerView.
class ItemAdapter(private val items: List<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()  {
    //Clase interna que representa cada elemento del RecyclerView
    inner class ItemViewHolder (private val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root){
        //Esta funcion se encarga de vincular los datos del objeto
        //a la vista correspondiente
        fun bind(item: Item) {
            //Se establece la imagen correspondiente al objeto
            binding.imageView.setImageResource(item.imagenId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       //Se infla el adapter correspondiente
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }
    //Vinculamos los datos a cada elemnto de la lista
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
    //Se devuelve el tamaño de la lista para saber cuantos elementos mostrar
    override fun getItemCount(): Int = items.size


}
