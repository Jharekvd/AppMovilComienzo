package com.vargas.tarea2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vargas.tarea2.DatasClass.Usuario
import com.vargas.tarea2.databinding.ActivityDetallesMostrarUsuarioBinding

class DetallesMostrarUsuario : AppCompatActivity() {
    lateinit var binding : ActivityDetallesMostrarUsuarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesMostrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detalles()
    }
    fun detalles(){
        //Recuperamos los datos del usuario desde el intent
       val usuario = intent.getSerializableExtra("usuario") as Usuario
        binding.textViewNombre.text=" Nombre: ${usuario.nombre} ${usuario.apellidos}"
        binding.textViewEdadAnio.text="Genero favorito: ${usuario.generoFavorito} Edad: ${usuario.edad}"
        binding.textViewPhone.text=" Telefono: ${usuario.phone}"
        binding.textViewEmail.text=" Email: ${usuario.email}"
        binding.textViewPassword.text=" Contraseña: ${usuario.password}"

    }
}