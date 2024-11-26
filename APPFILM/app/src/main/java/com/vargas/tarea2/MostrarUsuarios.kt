package com.vargas.tarea2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.vargas.tarea2.Adapters.UsuarioAdapter
import com.vargas.tarea2.DatasClass.Usuario
import com.vargas.tarea2.databinding.ActivityMostrarUsuariosBinding
import com.vargas.tarea2.databinding.MostrarUsuariosItemBinding
import com.vargas.tarea2.ui.MostrarUsuariosViewModel

class MostrarUsuarios : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarUsuariosBinding
    private val viewModel: MostrarUsuariosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarUsuariosBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //Configurar el reciclerView

        binding.reciclerviewUsuarios.layoutManager = LinearLayoutManager(this)
        //Configurar el ViewModel llamamos al metodo mostrarUsuarios
        viewModel.mostrarUsuario(this)
        //Observar los cambios en la lista de usuarios y actualizar el reciclerView
        //con el nuevo contenido de la lista de usuarios
        viewModel.usuarios.observe(this) { usuarios ->
            //Actualizar el reciclerView
            binding.reciclerviewUsuarios.adapter = UsuarioAdapter(usuarios){Usuario ->
                //Aqui se maneja el click en el usuario
                abrirDetalleUsuario(Usuario)
            }
        }

    }
    //Metodo para abrir el detalle del usuario cuando se hace click en el TEXTO DEL USUARIO
    //pasamos el usuario como argumento
    private fun abrirDetalleUsuario(usuario: Usuario) {
        //Creamos un intent para abrir la actividad DetallesMostrarUsuario
        val intent = Intent(this, DetallesMostrarUsuario::class.java)
        //Pasamos el usuario como extra en el intent
        intent.putExtra("usuario", usuario)
        //Cargamos la actividad
        startActivity(intent)
    }
    }
