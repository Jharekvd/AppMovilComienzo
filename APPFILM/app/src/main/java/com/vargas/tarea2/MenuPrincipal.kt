package com.vargas.tarea2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vargas.tarea2.Adapters.SeccionAdapter
import com.vargas.tarea2.databinding.ActivityMenuPrincipalBinding
import com.vargas.tarea2.ui.MenuPrincipalViewModel

class MenuPrincipal : AppCompatActivity() {
    private lateinit var binding: ActivityMenuPrincipalBinding
    private val viewModel: MenuPrincipalViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar);

        binding.reciclerView.layoutManager= LinearLayoutManager(this)
        viewModel.secciones.observe(this) { secciones ->
            binding.reciclerView.adapter= SeccionAdapter(secciones)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return true;
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
     return when (item.itemId){
            R.id.configuracion ->{
                Toast.makeText(this,"Seleccionaste la opciones configuracion",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.annadirusuario ->{
                Toast.makeText(this,"Seleccionaste la opciones añadir usuario",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainResgitrar::class.java)
                startActivity(intent)
                true
            }
            R.id.mostrarUsuarios ->{
                Toast.makeText(this,"Seleccionaste la opciones mostrar usuarios",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MostrarUsuarios::class.java)
                startActivity(intent)
                true
            }
            R.id.BD->{
                Toast.makeText(this,"Seleccionaste la opciones BD",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MostrarBD::class.java)
                startActivity(intent)
                true
            }
         else -> super.onOptionsItemSelected(item)
     }
     }
}