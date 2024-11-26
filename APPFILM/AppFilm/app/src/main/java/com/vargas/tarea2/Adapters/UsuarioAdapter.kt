package com.vargas.tarea2.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vargas.tarea2.DatasClass.Usuario
import com.vargas.tarea2.databinding.MostrarUsuariosBinding

class UsuarioAdapter (private val usuarios: List<Usuario>) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>()  {
    inner class UsuarioViewHolder(private val binding: MostrarUsuariosBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(usuario: Usuario) {
            binding.textViewNombreUser.text = """
                Usuario: ${usuario.nombre} ${usuario.apellidos}""".trimIndent()
            //Poner """ en un string permite que crear una cadena multilinea y hace los saltos de linea y el .trimIndent() elimina los espacios en blanco
            binding.textViewDatosUser.text ="""
                Email: ${usuario.email}
                Telefono: ${usuario.phone}
                Edad: ${usuario.edad}
                Año de nacimiento: ${usuario.annio}
            """.trimIndent()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = MostrarUsuariosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsuarioViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.bind(usuarios[position])
    }
    override fun getItemCount(): Int=usuarios.size


}