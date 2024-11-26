package com.vargas.tarea2.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vargas.tarea2.DatasClass.Usuario
import com.vargas.tarea2.databinding.MostrarUsuariosItemBinding

class UsuarioAdapter (private val usuarios: List<Usuario>,private val onUsuarioClick: (Usuario) -> Unit) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>()  {
    //private val onUsuarioClick: (Usuario) -> Unit hacemos un callback para manejar el click en el usuario
    //
    inner class UsuarioViewHolder(private val binding: MostrarUsuariosItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(usuario: Usuario) {
            binding.textViewNombreUser.text = """
                Usuario: ${usuario.nombre} ${usuario.apellidos}""".trimIndent()
            //Configuramos el click en el usuario
            binding.textViewNombreUser.setOnClickListener{
               //Cuando se hace click en el usuario se llama al callback
                onUsuarioClick(usuario)
            }
            //Poner """ en un string permite que crear una cadena multilinea y hace los saltos de linea y el .trimIndent() elimina los espacios en blanco
           /* binding.textViewDatosUser.text ="""
                Email: ${usuario.email}
                Telefono: ${usuario.phone}
                Edad: ${usuario.edad}
                Año de nacimiento: ${usuario.annio}
            """.trimIndent()*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = MostrarUsuariosItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsuarioViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.bind(usuarios[position])
    }
    override fun getItemCount(): Int=usuarios.size


}