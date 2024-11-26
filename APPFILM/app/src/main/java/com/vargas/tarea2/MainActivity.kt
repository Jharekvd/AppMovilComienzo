package com.vargas.tarea2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.vargas.tarea2.databinding.ActivityMainBinding
import com.vargas.tarea2.ui.MainViewModelMain

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.buttonResgister.setOnClickListener {
            //Pasamos al activity de registrar
            val intent = Intent(this, MainResgitrar::class.java)
            startActivity(intent)
        }
        _binding.Iniciar.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
            //Mostramos el archivo en el log
            val email = _binding.editTextTextEmailAddress.text.toString()
            val password = _binding.editTextTextPassword.text.toString()
            Toast.makeText(this, "Bienvenido a Movie $email $password", Toast.LENGTH_SHORT).show()
            _binding.editTextTextEmailAddress.text.clear()
            _binding.editTextTextPassword.text.clear()
            /*Toast.makeText(this, "Email: $email", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Password: $password", Toast.LENGTH_SHORT).show()*/
        }
    }
}