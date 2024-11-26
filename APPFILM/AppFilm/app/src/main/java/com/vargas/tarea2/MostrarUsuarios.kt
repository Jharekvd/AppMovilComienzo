package com.vargas.tarea2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.vargas.tarea2.Adapters.UsuarioAdapter
import com.vargas.tarea2.databinding.ActivityMostrarUsuariosBinding
import com.vargas.tarea2.ui.MostrarUsuariosViewModel

class MostrarUsuarios : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarUsuariosBinding
    private val viewModel: MostrarUsuariosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.reciclerviewUsuarios.layoutManager = LinearLayoutManager(this)
        viewModel.mostrarUsuarios(this)
        viewModel.usuarios.observe(this) { usuarios ->
            binding.reciclerviewUsuarios.adapter = UsuarioAdapter(usuarios)
        }
    }
}